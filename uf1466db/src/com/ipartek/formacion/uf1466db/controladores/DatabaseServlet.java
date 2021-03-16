package com.ipartek.formacion.uf1466db.controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.uf1466db.accesodatos.Basedatos;
import com.ipartek.formacion.uf1466db.accesodatos.BasedatosJdbc;
import com.ipartek.formacion.uf1466db.configuracion.Config;

@WebServlet("/ddbb")
public class DatabaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Basedatos DB = BasedatosJdbc.getInstancia();

	private static final Logger LOGGER = Logger.getLogger(DatabaseServlet.class.getName());

	private static final String UPLOAD_DIRECTORY = "database";

	private static Process runProcess = null;

	private static TreeMap<Integer, String> listaDB = new TreeMap<>();
	private static ArrayList<String> tablas = new ArrayList<>();

	private static final String MYSQL_RESTORE = "mysql -u " + Config.usuario + " -p" + Config.password + " ";
	private static final String MYSQL_DUMP = "mysqldump -u " + Config.usuario + " -p" + Config.password;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator;
		LOGGER.info(uploadPath);

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		// Listado de las BBDD de MySQL
		listaDB = DB.obtenerBaseDatos("sys");

		System.out.println(listaDB);

		request.setAttribute("listaDB", listaDB.values());
		request.getRequestDispatcher(Config.PATH_VISTAS + "formulario.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8
		request.setCharacterEncoding("utf-8");

		String indiceDb = request.getParameter("dbOrigen");
		LOGGER.info("Indice: " + indiceDb);

		String dbOrigen = listaDB.get(Integer.parseInt(indiceDb));
		LOGGER.info("BBDD Origen: " + dbOrigen);

		String dbDestino = request.getParameter("nombre");
		LOGGER.info("BBDD Destino: " + dbDestino);

		if (dbOrigen.equalsIgnoreCase(dbDestino)) {
			LOGGER.warning("dbOrigen " + dbOrigen + " = " + dbDestino);
			request.setAttribute("alertaTexto",
					"ERROR: La BB.DD. origen y destino no pueden tener el mismo nombre (" + dbDestino + ").");
			request.setAttribute("alertaNivel", "danger");
			doGet(request, response);
		}

		// Path donde dejar el BACKUP de la BBDD ORIGEN
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY + File.separator;
		LOGGER.info(uploadPath);

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		// Backup de la BBDD existente (BBDD ORIGEN)
		backupBaseDatos(dbOrigen, uploadPath);

		// DROP DATABASE (BBDD DESTINO) IF EXISTS
		LOGGER.info("DROP DATABASE IF EXISTS " + dbDestino);
		boolean borrarDB = DB.borrarBaseDatos(dbOrigen, dbDestino);

		if (borrarDB) {
			LOGGER.info("Borrada nueva BBDD. Todo correcto");
		} else {
			LOGGER.info("NO borrada nueva BBDD. Ha habido algún error");
		}

		// CREATE DATABASE nueva (BBDD DESTINO)
		LOGGER.info("CREATE DATABASE " + dbDestino);

		boolean crearDB = DB.crearBaseDatos(dbOrigen, dbDestino);

		if (crearDB) {
			LOGGER.info("Creada nueva BBDD. Todo correcto");
		} else {
			LOGGER.info("NO creada nueva BBDD. Ha habido algún error");
		}

		// Restore BBDD ORIGEN en la BBDD DESTINO
		restoreBaseDatos(dbDestino, uploadPath);

		// Listado de las BBDD de MySQL
		listaDB = DB.obtenerBaseDatos(dbDestino);
		request.setAttribute("listaDB", listaDB.values());

		// Listado de Tablas de la BBDD DESTINO
		tablas = DB.obtenerTablas(dbDestino);

		// Mostrar la vista con el resultado
		Integer key = obtenerPosicion(dbDestino);

		request.setAttribute("tablas", tablas);
		request.setAttribute("dbDestino", dbDestino.toLowerCase());
		request.setAttribute("indicelistaDB", key);

		request.setAttribute("alertaTexto", "ATENCIÓN: BB.DD ORIGEN '" + dbOrigen
				+ "' migrada con éxito a la BB.DD. DESTINO '" + dbDestino.toLowerCase() + "'.");
		request.setAttribute("alertaNivel", "success");

		request.setAttribute("resultadoTexto", "BB.DD ORIGEN '" + dbOrigen + "' migrada con éxito a la BB.DD. DESTINO '"
				+ dbDestino.toLowerCase() + "'.");
		request.getRequestDispatcher(Config.PATH_VISTAS + "migracion.jsp").forward(request, response);
	}

	private Integer obtenerPosicion(String dbDestino) {
		Integer key = 0;
		for (Map.Entry<Integer, String> entry : listaDB.entrySet()) {
			key = entry.getKey();
			String value = entry.getValue();

			if (value.equalsIgnoreCase(dbDestino)) {
				break;
			}
		}
		return key;
	}

	private void backupBaseDatos(String dbOrigen, String uploadPath) throws IOException, FileNotFoundException {
		// Backup de la BBDD existente (BBDD ORIGEN)
		LOGGER.info(MYSQL_DUMP + " " + dbOrigen);

		runProcess = Runtime.getRuntime().exec(MYSQL_DUMP + " " + dbOrigen);
		try (InputStream is = runProcess.getInputStream();
				FileOutputStream fos = new FileOutputStream(uploadPath + Config.database_dump)) {
			byte[] buffer = new byte[1000];

			int leido;
			while ((leido = is.read(buffer)) > 0) {
				fos.write(buffer, 0, leido);
			}
		}

		int processComplete;

		try {
			processComplete = runProcess.waitFor();
			if (processComplete == 0) {
				LOGGER.info("Backup de la BBDD. Todo correcto");
			} else {
				LOGGER.info("Backup de la BBDD. Ha habido algún error");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	private void restoreBaseDatos(String dbDestino, String uploadPath) throws IOException, FileNotFoundException {
		int processComplete;
		// Restore BBDD ORIGEN en la BBDD DESTINO
		LOGGER.info(MYSQL_RESTORE + dbDestino);

		runProcess = Runtime.getRuntime().exec(MYSQL_RESTORE + dbDestino);
		try (OutputStream os = runProcess.getOutputStream();
				FileInputStream fis = new FileInputStream(uploadPath + Config.database_dump)) {
			byte[] buffer = new byte[1000];

			int leido;
			while ((leido = fis.read(buffer)) > 0) {
				os.write(buffer, 0, leido);
			}
		}

		try {
			processComplete = runProcess.waitFor();
			if (processComplete == 0) {
				LOGGER.info("Restore de la BBDD. Todo correcto");
			} else {
				LOGGER.info("Restore de la BBDD. Ha habido algún error");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}