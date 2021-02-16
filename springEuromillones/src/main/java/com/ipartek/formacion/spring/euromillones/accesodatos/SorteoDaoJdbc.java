package com.ipartek.formacion.spring.euromillones.accesodatos;

import static com.ipartek.formacion.spring.euromillones.bibliotecas.Miscelania.derechaString;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import com.ipartek.formacion.spring.euromillones.entidades.Sorteo;

public class SorteoDaoJdbc implements SorteoDao {
	private String url, usuario, password;

	private static final SorteoDaoJdbc INSTANCIA = new SorteoDaoJdbc();

	private static final String SQL_SELECT = "SELECT id, numero1, numero2, numero3, numero4, numero5, estrella1, estrella2, fecha FROM sorteos";

	private static final String SQL_INSERT = "INSERT INTO sorteos (numero1, numero2, numero3, numero4, numero5, estrella1, estrella2, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_COUNT_ID = "SELECT COUNT(id) FROM sorteos";

	private SorteoDaoJdbc() {
		try {
			// Accedemos al fichero de configuración 'jdbc.prpoerties' para aobtener los
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

	public static SorteoDaoJdbc getInstancia() {
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
	public Iterable<Sorteo> obtenerTodos() {
		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Sorteo> sorteos = new ArrayList<>();
			Sorteo sorteo;
			Date fecha = null;

			while (rs.next()) {
				fecha = rs.getDate("fecha");
				LocalDate fechaSorteo = new java.sql.Date(fecha.getTime()).toLocalDate();

				sorteo = new Sorteo(rs.getLong("id"), rs.getString("numero1"), rs.getString("numero2"),
						rs.getString("numero3"), rs.getString("numero4"), rs.getString("numero5"),
						rs.getString("estrella1"), rs.getString("estrella2"), fechaSorteo);

				sorteos.add(sorteo);
			}

			return sorteos;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los registros de sorteos", e);
		}
	}

	@Override
	public Sorteo agregar(Sorteo sorteo) {
		try (Connection con = getConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, derechaString(("00" + sorteo.getNumero_1()), 2));
			pst.setString(2, derechaString(("00" + sorteo.getNumero_2()), 2));
			pst.setString(3, derechaString(("00" + sorteo.getNumero_3()), 2));
			pst.setString(4, derechaString(("00" + sorteo.getNumero_4()), 2));
			pst.setString(5, derechaString(("00" + sorteo.getNumero_5()), 2));

			pst.setString(6, derechaString(("00" + sorteo.getEstrella_1()), 2));
			pst.setString(7, derechaString(("00" + sorteo.getEstrella_2()), 2));

			Date fecha = null;
			if (sorteo.getFechaSorteo() != null) {
				fecha = Date.valueOf(sorteo.getFechaSorteo());
			}
			pst.setDate(8, fecha);

			int num = pst.executeUpdate();

			if (num != 1) {
				throw new AccesoDatosException("Se ha insertado más o menos de un registro ¿?¿?¿?¿?");
			}

			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {
				sorteo.setId(rs.getLong(1));
			} else {
				throw new AccesoDatosException("No se han podido obtener las claves autogeneradas");
			}

			return sorteo;

		} catch (Exception e) {
			throw new AccesoDatosException("Error al agregar el registro a sorteos", e);
		}
	}

	@Override
	public int numeroRegistros() {
		try (Connection con = getConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_COUNT_ID);) {

			int countRegistros = 0;

			while (rs.next()) {

				countRegistros = rs.getInt(1);
			}

			return countRegistros;

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de sorteos de Euromillones", e);
		}
	}
}
