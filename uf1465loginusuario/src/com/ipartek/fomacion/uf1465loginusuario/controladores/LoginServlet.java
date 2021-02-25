package com.ipartek.fomacion.uf1465loginusuario.controladores;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.uf1465loginusuario.accesodatos.UsuarioDao;
import com.ipartek.formacion.uf1465loginusuario.bibliotecas.Password;
import com.ipartek.formacion.uf1465loginusuario.entidades.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(LoginServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UsuarioDao dao = Configuracion.daoUsuario;

		Usuario usuario = dao.obtenerPorEmail(email);

		if (usuario == null || !email.equals(usuario.getEmail())) {
			request.setAttribute("mensaje", "El usuario o la contraseña son incorrectos");
			request.setAttribute("nivel", "danger");

			request.setAttribute("email", email);

			doGet(request, response);
			return;
		}

		LOG.info(usuario.toString());

		String hash = Password.obtenerHash(password);

		String longitud = String.valueOf(hash.length());

		LOG.info(password);
		LOG.info(hash);
		LOG.info(longitud);

		if (hash.equals(usuario.getPassword())) {
			request.getSession().setAttribute("usuario", usuario);

			LOG.info("request.getContextPath() " + request.getContextPath());

			if ("ROOT".equals(usuario.getRol().getNombre())) {
				request.setAttribute("rolUsuario", usuario.getRol().getNombre());
				LOG.info(usuario.getRol().getNombre());
				// response.sendRedirect(request.getContextPath() + "/admin/usuarios");
				response.sendRedirect(request.getContextPath() + "/admin/inicio");
			} else {
				response.sendRedirect(request.getContextPath() + "/inicio");
			}
		} else {
			request.setAttribute("mensaje", "El usuario o la contraseña son incorrectos");
			request.setAttribute("nivel", "danger");

			request.setAttribute("email", email);

			doGet(request, response);
		}
	}
}