package com.ipartek.formacion.uf1466db.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

import com.ipartek.formacion.uf1466db.configuracion.Config;

public class BasedatosJdbc implements Basedatos {

	private static final String SHOW_DATABASES = "show databases";
	private static final String SHOW_TABLES = "show tables";
	private static final String CREATE_BATABASE = "CREATE DATABASE ";
	private static final String DROP_DATABASE = "DROP DATABASE IF EXISTS ";

	private String url, usuario, password;

	private String stringSQL;

	private static final BasedatosJdbc INSTANCIA = new BasedatosJdbc();

	private BasedatosJdbc() {
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
			usuario = Config.usuario;
			password = Config.password;

		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido leer el fichero de configuración jdbc.properties", e);
		}
	}

	public static BasedatosJdbc getInstancia() {
		return INSTANCIA;
	}

	private Connection getConexion(String basedatos) {
		try {
			String url_basedatos = url.replaceFirst("XXXXXX", basedatos);

			return DriverManager.getConnection(url_basedatos, usuario, password);
		} catch (Exception e) {
			throw new AccesoDatosException("Fallo de conexión", e);
		}
	}

	@Override
	public ArrayList<String> obtenerTablas(String basedatos) {
		try (Connection con = getConexion(basedatos);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SHOW_TABLES)) {
			ArrayList<String> tablas = new ArrayList<>();
			String tabla = "";

			while (rs.next()) {
				tabla = rs.getString(1);
				tablas.add(tabla);
			}

			return tablas;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de tablas", e);
		}
	}

	@Override
	public TreeMap<Integer, String> obtenerBaseDatos(String basedatos) {
		try (Connection con = getConexion(basedatos);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SHOW_DATABASES)) {
			TreeMap<Integer, String> databases = new TreeMap<>();
			String db = "";
			int indice = 1;

			while (rs.next()) {
				db = rs.getString(1);
				databases.put(indice, db);
				indice++;
			}

			return databases;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de bases de datos", e);
		}
	}

	@Override
	public boolean borrarBaseDatos(String dbOrigen, String dbDestino) {
		boolean isBorrado = false;

		String stringSQL = DROP_DATABASE + dbDestino;

		try (Connection con = getConexion(dbOrigen); PreparedStatement ps = con.prepareStatement(stringSQL);) {

			ps.executeUpdate();

			isBorrado = true;

		} catch (Exception e) {
			System.out.println("La BB.DD. " + dbDestino + " no existe.");
		}
		return isBorrado;
	}

	@Override
	public boolean crearBaseDatos(String dbOrigen, String dbDestino) {
		boolean isCreado = false;

		String stringSQL = CREATE_BATABASE + dbDestino;

		try (Connection con = getConexion(dbOrigen); PreparedStatement ps = con.prepareStatement(stringSQL);) {

			ps.executeUpdate();

			isCreado = true;
			return isCreado;
		} catch (Exception e) {
			System.out.println("La BB.DD. " + dbDestino + " no existe.");
			throw new AccesoDatosException("Error al crear la BB.DD " + dbDestino, e);
		}
	}

}
