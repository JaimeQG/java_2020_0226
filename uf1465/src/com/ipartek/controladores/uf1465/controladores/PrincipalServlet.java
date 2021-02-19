package com.ipartek.controladores.uf1465.controladores;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.uf1465.accesodatos.Dao;
import com.ipartek.formacion.uf1465.accesodatos.ProductoDaoMySql;
import com.ipartek.formacion.uf1465.entidades.Producto;

/**
 * Servlet implementation class PrincipalServlet
 */
@WebServlet("/principal")
public class PrincipalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(PrincipalServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Dao<Producto> dao = new ProductoDaoMySql();

		Iterable<Producto> productos = dao.obtenerTodos();

		LOG.log(Level.INFO, "Usuarios: {0}", productos);

		request.setAttribute("productos", productos);

		request.getRequestDispatcher("/WEB-INF/vistas/productos.jsp").forward(request, response);

		// KISS: Keep It Simple Stupid
		// DRY: Don't Repeat Yourself
		// https://tantacom.com/principios-diseno-software-kiss-dry-solid/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
