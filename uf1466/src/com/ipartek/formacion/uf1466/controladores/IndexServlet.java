package com.ipartek.formacion.uf1466.controladores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.uf1466.accesodatos.ClienteDao;
import com.ipartek.formacion.uf1466.accesodatos.ClienteDaoJdbc;
import com.ipartek.formacion.uf1466.bibliotecas.Miscelania;
import com.ipartek.formacion.uf1466.entidades.Cliente;

@WebServlet("/clientes")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(IndexServlet.class.getName());

	private static ClienteDao DAO = ClienteDaoJdbc.getInstancia();

	private static FileWriter fw;
	private static PrintWriter pw;
	private static String archivoCSV;
	private static String SPLIT = ";";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		Iterable<Cliente> clientes = DAO.obtenerTodos();

		if (clientes != null) {
			request.setAttribute("clientes", clientes);
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);

		} else {

			LOG.warning("No hay clientes en la BB.DD");

			request.setAttribute("alertaTexto", "No hay clientes !!!");
			request.setAttribute("alertaNivel", "warning");

			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		try {
			String file = Miscelania.nombreFicheroCSV();
			archivoCSV = getServletContext().getRealPath(file);
			// archivoCSV = getServletContext().getRealPath(Miscelania.nombreFicheroCSV());
			LOG.info(file);
			LOG.info(archivoCSV);

			fw = new FileWriter(archivoCSV);
			pw = new PrintWriter(fw);

			StringBuilder bld = new StringBuilder();
			Iterable<String> columnas = DAO.nombreColumnas();
			for (String columna : columnas) {
				bld.append(columna.toUpperCase() + SPLIT);
			}
			String cabecera = bld.toString();
			pw.println(cabecera);

			LOG.info(cabecera);

			Iterable<Cliente> clientes = DAO.obtenerTodos();

			for (Cliente cliente : clientes) {

				int isActivo = cliente.isActivo() ? 1 : 0;

				pw.println(cliente.getCodigo() + SPLIT + cliente.getNombre() + SPLIT + cliente.getEmail() + SPLIT
						+ cliente.getTelefono() + SPLIT + cliente.getDireccion() + SPLIT + cliente.getPoblacion()
						+ SPLIT + cliente.getCodigopostal() + SPLIT + cliente.getIdentificador() + SPLIT + isActivo
						+ SPLIT);
				LOG.info(cliente.toString());
			}

			pw.close();
			fw.close();

			request.setAttribute("alertaTexto",
					"El archivo CSV se ha exportado con exito en la siguiente ruta: " + archivoCSV);
			request.setAttribute("alertaNivel", "success");

			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			response.sendRedirect(request.getContextPath() + "/" + file);

			// doGet(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}