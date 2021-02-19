package com.ipartek.controladores.uf1465.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.formacion.uf1465.accesodatos.Dao;
import com.ipartek.formacion.uf1465.accesodatos.ProductoDaoMySql;
import com.ipartek.formacion.uf1465.entidades.Producto;

@WebServlet({ "/api/productos" })
public class ProductoApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Gson gson = new Gson();

	private static Dao<Producto> dao = new ProductoDaoMySql();

	private static final Logger LOGGER = Logger.getLogger(ProductoApiServlet.class.getName());

	// /api/productos Todos los registros
	// /api/productos/1 Registro con id concreto
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		LOGGER.log(Level.INFO, "GET: Lista de productos ");
		out.write(gson.toJson(dao.obtenerTodos()));

	}
}