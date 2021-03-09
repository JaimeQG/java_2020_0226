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

@WebServlet("/libro-detalle")
public class LibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LibrosServlet.class.getName());

	private static final LibroDao DAO = LibroDaoJdbc.getInstancia();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Recoger información de la petición

		String id = request.getParameter("id");

		// 2. Poner información dentro de un modelo
		// 3. Tomar decisiones según lo recibido

		if (id != null) {
			Libro libro = DAO.obtenerPorId(Long.parseLong(id));
			LOGGER.info(libro.toString());

			// 4. Generar modelo para la vista

			request.setAttribute("libro", libro);
		}

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/WEB-INF/vistas/libro.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 5. Redirigir a otra vista
		request.getRequestDispatcher("/libros").forward(request, response);
	}

}
