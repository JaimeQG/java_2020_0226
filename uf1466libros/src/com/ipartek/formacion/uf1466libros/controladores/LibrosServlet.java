package com.ipartek.formacion.uf1466libros.controladores;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.uf1466libros.accesodatos.LibroDao;
import com.ipartek.formacion.uf1466libros.accesodatos.LibroDaoJdbc;
import com.ipartek.formacion.uf1466libros.entidades.Libro;

@WebServlet("/libros")
public class LibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LibrosServlet.class.getName());

	private static final LibroDao DAO = LibroDaoJdbc.getInstancia();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		Iterable<Libro> libros = DAO.obtenerTodos();

		if (libros != null) {
			request.setAttribute("libros", libros);
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);

		} else {

			LOGGER.warning("No hay libros en la BB.DD");

			request.setAttribute("alertaTexto", "No hay clientes !!!");
			request.setAttribute("alertaNivel", "warning");

			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		doGet(request, response);
	}

}