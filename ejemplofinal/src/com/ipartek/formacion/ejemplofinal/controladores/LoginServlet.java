package com.ipartek.formacion.ejemplofinal.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ejemplofinal.entidades.Alerta;
import com.ipartek.formacion.ejemplofinal.entidades.Usuario;

/**
 * Representa el servlet para hacer login en la aplicación del supermercado
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(Config.PATH_VISTAS + "login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Usuario usuario = new Usuario(null, email, password, null);

		if (Config.usuarioNegocio.validarUsuario(usuario)) {
			request.getSession().setAttribute("alerta", new Alerta("success", "Login correcto"));
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect(request.getContextPath() + "/index");
		} else {
			request.setAttribute("email", email);
			request.setAttribute("alerta", new Alerta("danger", "El usuario o la contraseña son incorrectos"));
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(Config.PATH_VISTAS + "login.jsp").forward(request, response);
		}
	}

}