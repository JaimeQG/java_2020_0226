package com.ipartek.fomacion.uf1465loginusuario.controladores;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mensajeTexto;

		Date fechaActual = new Date();
		// Formateando la fecha:
		DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		mensajeTexto = ("Son las: " + formatoHora.format(fechaActual) + " del día: "
				+ formatoFecha.format(fechaActual));

		request.setAttribute("mensajeTexto", mensajeTexto);

		request.getRequestDispatcher("/WEB-INF/vistas/inicio.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}