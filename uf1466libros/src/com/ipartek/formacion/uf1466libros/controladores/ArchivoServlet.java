package com.ipartek.formacion.uf1466libros.controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ipartek.formacion.uf1466libros.accesodatos.AccesoDatosException;

@WebServlet("/restore")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ArchivoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ArchivoServlet.class.getName());

	private static final String UPLOAD_DIRECTORY = "database_backup";

	private String usuario;
	private String password;
	private String mysqlpath;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/vistas/archivo.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar codificación de entrada de datos de formulario de Windows-1252 a UTF8

		request.setCharacterEncoding("utf-8");

		try {
			// Accedemos al fichero de configuración 'jdbc.prpoerties' para obtener los
			// datos de conexión con
			// la BBDD:
			// - usuario
			// - password
			Properties props = new Properties();
			props.load(getClass().getClassLoader().getResourceAsStream("jdbc.properties"));

			Class.forName(props.getProperty("jdbc.driver"));

			usuario = props.getProperty("jdbc.usuario");
			password = props.getProperty("jdbc.password");
			mysqlpath = props.getProperty("jdbc.mysqlpath");

		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido leer el fichero de configuración jdbc.properties", e);
		}

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		LOGGER.info(uploadPath);
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdir();

		String nombreFichero = null;
		String fileRestore = "";

		for (Part part : request.getParts()) {
			nombreFichero = part.getSubmittedFileName();

			LOGGER.info("Nombre de fichero: [" + nombreFichero + "]");

			if (nombreFichero != null && nombreFichero.trim().length() > 0) {
				LOGGER.info("Nombre de fichero ACEPTADO: [" + nombreFichero + "]");
				fileRestore = uploadPath + File.separator + nombreFichero;

				LOGGER.info(fileRestore);
				part.write(uploadPath + File.separator + nombreFichero);
			}
		}

		OutputStream os = null;
		FileInputStream fis = null;

		LOGGER.info(mysqlpath + " -u " + usuario + " -p" + password);
		String cmdRestore = mysqlpath + " -u " + usuario + " -p" + password;

		try {
			Process p = Runtime.getRuntime().exec(cmdRestore);

			os = p.getOutputStream();
			fis = new FileInputStream(fileRestore);
			byte[] buffer = new byte[1000];

			int leido = fis.read(buffer);
			while (leido > 0) {
				os.write(buffer, 0, leido);
				leido = fis.read(buffer);
			}

			int processComplete = p.waitFor();

			if (processComplete == 0) {
				System.out.println("Restore BBDD hecho");
			}

			os.flush();
			os.close();
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("alertaTexto", "Ha fallado el restore de la BB.DD.");
			request.setAttribute("alertaNivel", "danger");
			request.getRequestDispatcher("/error").forward(request, response);

			return;
		}

		// 5. Redirigir a otra vista
		request.setAttribute("alertaTexto", "Restore de la BB.DD hecho correctamente");
		request.setAttribute("alertaNivel", "success");

		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/libros").forward(request, response);
	}

}