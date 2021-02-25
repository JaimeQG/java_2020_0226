package com.ipartek.formacion.uf1465loginusuario.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ipartek.formacion.uf1465loginusuario.entidades.Rol;
import com.ipartek.formacion.uf1465loginusuario.entidades.Usuario;

public class UsuarioDaoMySql implements UsuarioDao {

	private static final String SQL_SELECT = "SELECT u.id u_id, usuario, password, r.id r_id, nombre r_nombre, descripcion r_descripcion FROM usuarios u JOIN roles r ON u.roles_id = r.id";
	private static final String SQL_SELECT_USUARIO = SQL_SELECT + " WHERE usuario = ?";

	private DataSource dataSource = null;

	public UsuarioDaoMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/uf1465login");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el JNDI de usuarioservlets", e);
		}
	}

	// SINGLETON

	private final static UsuarioDaoMySql INSTANCIA = new UsuarioDaoMySql();

	public static UsuarioDaoMySql getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Iterable<Usuario> obtenerTodos() {
		try (Connection con = dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Usuario> usuarios = new ArrayList<>();
			Usuario usuario;
			Rol rol;

			while (rs.next()) {
				rol = new Rol(rs.getLong("r_id"), rs.getString("r_nombre"), rs.getString("r_descripcion"));
				usuario = new Usuario(rs.getLong("u_id"), rs.getString("usuario"), rs.getString("password"), rol);

				usuarios.add(usuario);
			}

			return usuarios;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se han podido obtener todos los registros de usuarios", e);
		} catch (Exception e) {
			throw new AccesoDatosException(
					"ERROR NO ESPERADO: No se han podido obtener todos los registros de usuarios", e);
		}
	}

	@Override
	public Usuario obtenerPorId(Long id) {

		return UsuarioDao.super.obtenerPorId(id);
	}

	@Override
	public Usuario insertar(Usuario t) {

		return UsuarioDao.super.insertar(t);
	}

	@Override
	public Usuario modificar(Usuario t) {

		return UsuarioDao.super.modificar(t);
	}

	@Override
	public void borrar(Long id) {

		UsuarioDao.super.borrar(id);
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		try (Connection con = dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_USUARIO);) {

			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			Usuario usuario = null;
			Rol rol;

			if (rs.next()) {
				rol = new Rol(rs.getLong("r_id"), rs.getString("r_nombre"), rs.getString("r_descripcion"));
				usuario = new Usuario(rs.getLong("u_id"), rs.getString("usuario"), rs.getString("password"), rol);
			}

			return usuario;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener el usuario cuyo email es: " + email, e);
		} catch (Exception e) {
			throw new AccesoDatosException(
					"ERROR NO ESPERADO: No se ha podido obtener el usuario cuyo email es: " + email, e);
		}
	}

}
