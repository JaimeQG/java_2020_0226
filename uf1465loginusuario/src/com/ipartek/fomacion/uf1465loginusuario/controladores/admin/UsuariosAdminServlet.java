package com.ipartek.fomacion.uf1465loginusuario.controladores.admin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.fomacion.uf1465loginusuario.controladores.Configuracion;
import com.ipartek.formacion.uf1465loginusuario.accesodatos.UsuarioDao;
import com.ipartek.formacion.uf1465loginusuario.entidades.Usuario;

@WebServlet("/admin/usuarios")
public class UsuariosAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(UsuariosAdminServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioDao dao = Configuracion.daoUsuario;

		Iterable<Usuario> usuarios = dao.obtenerTodos();

		LOG.log(Level.INFO, "Usuarios: {0}", usuarios);

		request.setAttribute("rolUsuario", "ROOT");
		request.setAttribute("usuarios", usuarios);

		request.getRequestDispatcher("/WEB-INF/vistas/admin/usuarios.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}