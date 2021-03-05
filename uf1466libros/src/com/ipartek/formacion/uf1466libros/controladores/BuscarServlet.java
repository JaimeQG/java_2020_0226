package com.ipartek.formacion.uf1466libros.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.uf1466libros.accesodatos.LibroDao;
import com.ipartek.formacion.uf1466libros.accesodatos.LibroDaoJdbc;
import com.ipartek.formacion.uf1466libros.entidades.Libro;

@WebServlet("/buscar")
public class BuscarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LibrosServlet.class.getName());

	private static final LibroDao DAO = LibroDaoJdbc.getInstancia();

	private static final String ISBN = "ISBN";
	private static final String TITULO = "TITULO";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/WEB-INF/vistas/buscar.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String buscarPor = request.getParameter("criterio");
		LOGGER.info(buscarPor);

		String textoBuscar = request.getParameter("texto");
		LOGGER.info(textoBuscar);

		Libro libro = null;
		Iterable<Libro> libros = null;
		ArrayList<Libro> librosArray = new ArrayList<>();

		switch (buscarPor.toUpperCase()) {
		case ISBN:
			LOGGER.info(ISBN);
			libro = DAO.buscarPorISBN(textoBuscar);

			if (libro != null) {
				LOGGER.info(libro.toString());
				librosArray.add(libro);
				request.setAttribute("libros", librosArray);
			}
			break;

		case TITULO:
			LOGGER.info(TITULO);
			libros = DAO.buscarPorTitulo(textoBuscar);
			if (libros != null) {
				LOGGER.info(libros.toString());
				request.setAttribute("libros", libros);
			}
			break;

		default:
			break;
		}

		// 5. Redirigir a otra vista

		request.getRequestDispatcher("/WEB-INF/vistas/resultadoBusqueda.jsp").forward(request, response);

	}

}
