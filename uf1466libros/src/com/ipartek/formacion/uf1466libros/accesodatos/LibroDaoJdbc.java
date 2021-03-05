package com.ipartek.formacion.uf1466libros.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.ipartek.formacion.uf1466libros.entidades.Autor;
import com.ipartek.formacion.uf1466libros.entidades.Libro;

public class LibroDaoJdbc implements LibroDao {

	private static final String SQL_SELECT = "SELECT l.id l_id, l.titulo l_titulo, l.ISBN l_ISBN, a.id a_id, a.nombre a_nombre, a.apellidos a_apellidos FROM libros AS l\r\n"
			+ "LEFT JOIN autores AS a ON l.autores_id = a.id ";

	private static final String SQL_SELECT_ID = SQL_SELECT + "WHERE l.id = ?";

	private static final String SQL_SELECT_ISBN = SQL_SELECT + "WHERE l.ISBN = ?";

	private static final String SQL_SELECT_TITULO = SQL_SELECT + "WHERE l.titulo LIKE ?";

	private String url, usuario, password;

	private static final LibroDaoJdbc INSTANCIA = new LibroDaoJdbc();

	private LibroDaoJdbc() {
		try {
			// Accedemos al fichero de configuración 'jdbc.prpoerties' para obtener los
			// datos de conexión con
			// la BBDD:
			// - url
			// - usuario
			// - password
			Properties props = new Properties();
			props.load(getClass().getClassLoader().getResourceAsStream("jdbc.properties"));

			Class.forName(props.getProperty("jdbc.driver"));

			url = props.getProperty("jdbc.url");
			usuario = props.getProperty("jdbc.usuario");
			password = props.getProperty("jdbc.password");

		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido leer el fichero de configuración jdbc.properties", e);
		}
	}

	public static LibroDaoJdbc getInstancia() {
		return INSTANCIA;
	}

	private Connection getConexion() {
		try {
			return DriverManager.getConnection(url, usuario, password);
		} catch (Exception e) {
			throw new AccesoDatosException("Fallo de conexión", e);
		}
	}

	@Override
	public Iterable<Libro> obtenerTodos() {
		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Libro> libros = new ArrayList<>();
			Libro libro;
			Autor autor;

			while (rs.next()) {
				autor = new Autor(rs.getLong("a_id"), rs.getString("a_nombre"), rs.getString("a_apellidos"));
				libro = new Libro(rs.getLong("l_id"), rs.getString("l_titulo"), rs.getString("l_ISBN"), autor);

				libros.add(libro);
			}

			return libros;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de libros", e);
		}
	}

	@Override
	public Libro obtenerPorId(Long id) {
		try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				Libro libro = null;
				Autor autor;

				if (rs.next()) {

					libro = new Libro(rs.getLong("l_id"), rs.getString("l_titulo"), rs.getString("l_ISBN"), null);
					autor = new Autor(rs.getLong("a_id"), rs.getString("a_nombre"), rs.getString("a_apellidos"));

					libro.setAutor(autor);
				}

				return libro;
			}
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener el registro de libro", e);
		}
	}

	@Override
	public Libro buscarPorISBN(String ISBN) {
		try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ISBN);) {

			ps.setString(1, ISBN);

			try (ResultSet rs = ps.executeQuery()) {
				Libro libro = null;
				Autor autor = null;

				if (rs.next()) {

					libro = new Libro(rs.getLong("l_id"), rs.getString("l_titulo"), rs.getString("l_ISBN"), null);
					autor = new Autor(rs.getLong("a_id"), rs.getString("a_nombre"), rs.getString("a_apellidos"));

					libro.setAutor(autor);
				}

				return libro;
			}
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener el registro de libro", e);
		}
	}

	@Override
	public Iterable<Libro> buscarPorTitulo(String titulo) {
		try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_TITULO);) {

			ps.setString(1, "%" + titulo + "%");

			try (ResultSet rs = ps.executeQuery()) {
				ArrayList<Libro> libros = new ArrayList<>();
				Libro libro = null;
				Autor autor;

				while (rs.next()) {
					autor = new Autor(rs.getLong("a_id"), rs.getString("a_nombre"), rs.getString("a_apellidos"));
					libro = new Libro(rs.getLong("l_id"), rs.getString("l_titulo"), rs.getString("l_ISBN"), autor);

					libros.add(libro);
				}

				return libros;
			}
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener el registro de libro", e);
		}
	}
}
