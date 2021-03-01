package com.ipartek.formacion.uf1466.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.ipartek.formacion.uf1466.entidades.Cliente;

public class ClienteDaoJdbc implements ClienteDao {

	private static final String SQL_SELECT = "SELECT codigo, nombre, email, telefono, direccion, poblacion, codigopostal, identificador, activo FROM cliente";

	private String url, usuario, password;

	private static final ClienteDaoJdbc INSTANCIA = new ClienteDaoJdbc();

	private ClienteDaoJdbc() {
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

	public static ClienteDaoJdbc getInstancia() {
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
	public Iterable<Cliente> obtenerTodos() {
		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Cliente> clientes = new ArrayList<>();
			Cliente cliente;

			while (rs.next()) {
				cliente = new Cliente(rs.getInt("codigo"), rs.getString("nombre"), rs.getString("email"),
						rs.getInt("telefono"), rs.getString("direccion"), rs.getString("poblacion"),
						rs.getInt("codigopostal"), rs.getString("identificador"), rs.getBoolean("activo"));

				clientes.add(cliente);
			}

			return clientes;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de clientes", e);
		}
	}

	@Override
	public ArrayList<String> nombreColumnas() {
		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {

			ResultSetMetaData rsmd = rs.getMetaData();

			ArrayList<String> columnas = new ArrayList<>();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnas.add(rsmd.getColumnName(i));
			}

			return columnas;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de clientes", e);
		}
	}

}
