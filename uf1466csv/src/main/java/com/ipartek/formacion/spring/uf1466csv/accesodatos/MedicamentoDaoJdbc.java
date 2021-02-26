package com.ipartek.formacion.spring.uf1466csv.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.ipartek.formacion.spring.uf1466csv.entidades.Medicamento;

public class MedicamentoDaoJdbc implements MedicamentoDao {
	private String url, usuario, password;

	private static final MedicamentoDaoJdbc INSTANCIA = new MedicamentoDaoJdbc();

	private static final String SQL_SELECT = "SELECT id, referencia, nombre, precio FROM medicamentos";

	private MedicamentoDaoJdbc() {
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

	public static MedicamentoDaoJdbc getInstancia() {
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
	public Iterable<Medicamento> obtenerTodos() {
		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Medicamento> medicamentos = new ArrayList<>();
			Medicamento medicamento;

			while (rs.next()) {
				medicamento = new Medicamento(rs.getLong("id"), rs.getString("referencia"), rs.getString("nombre"),
						rs.getBigDecimal("precio"));

				medicamentos.add(medicamento);
			}

			return medicamentos;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de medicamentos", e);
		}
	}

	@Override
	public ArrayList<String> nombreColumnas() {
		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {

			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("Total columnas: " + rsmd.getColumnCount());

			ArrayList<String> columnas = new ArrayList<>();

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnas.add(rsmd.getColumnName(i));
			}

			return columnas;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de medicamentos", e);
		}
	}

}
