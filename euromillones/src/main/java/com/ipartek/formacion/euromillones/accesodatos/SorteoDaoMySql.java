package com.ipartek.formacion.euromillones.accesodatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ipartek.formacion.euromillones.modelos.Sorteo;

public class SorteoDaoMySql implements Dao<Sorteo> {
	private static final String URL = "jdbc:mysql://localhost:3306/euromillones?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";

	private static final String SQL_SELECT = "SELECT * FROM sorteos";
	private static final String SQL_SELECT_ID = "SELECT * FROM sorteos WHERE id = ?";
	private static final String SQL_COUNT_ID = "SELECT COUNT(id) FROM sorteos";

	private static final String SQL_INSERT = "INSERT INTO sorteos (numero1, numero2, numero3, numero4, numero5, estrella1, estrella2, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySQL", e);
		}
	}

	// SINGLETON

	private SorteoDaoMySql() {

	}

	private final static SorteoDaoMySql INSTANCIA = new SorteoDaoMySql();

	public static SorteoDaoMySql getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Iterable<Sorteo> obtenerTodos() {
		return obtenerRegistros(SQL_SELECT);
	}

	private Iterable<Sorteo> obtenerRegistros(String consulta) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(consulta)) {

			ArrayList<Sorteo> euromillones = new ArrayList<>();
			Sorteo sorteo;

			while (rs.next()) {

				sorteo = mapper(rs);
				euromillones.add(sorteo);
			}

			return euromillones;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de sorteos de Euromillones", e);
		}
	}

	/*
	 * @Override public Medicamento obtenerPorId(Long id) { try (Connection con =
	 * DriverManager.getConnection(URL, USER, PASS); PreparedStatement ps =
	 * con.prepareStatement(SQL_SELECT_ID);) {
	 * 
	 * ps.setLong(1, id);
	 * 
	 * try (ResultSet rs = ps.executeQuery()) { Medicamento medicamento = null;
	 * 
	 * if (rs.next()) { medicamento = mapper(rs); }
	 * 
	 * return medicamento; }
	 * 
	 * } catch (SQLException e) { throw new
	 * AccesoDatosException("No se ha podido recibir el producto " + id, e); } }
	 * 
	 * @Override public void crear(Medicamento medicamento) { try (Connection con =
	 * DriverManager.getConnection(URL, USER, PASS); PreparedStatement ps =
	 * con.prepareStatement(SQL_INSERT);) {
	 * 
	 * ps.setString(1, medicamento.getReferencia()); ps.setString(2,
	 * medicamento.getNombre()); ps.setBigDecimal(3, medicamento.getPrecio());
	 * 
	 * int numeroRegistrosInsertados = ps.executeUpdate();
	 * 
	 * if (numeroRegistrosInsertados != 1) { throw new
	 * AccesoDatosException("Se han insertado " + numeroRegistrosInsertados +
	 * " registros"); } } catch (SQLException e) { throw new
	 * AccesoDatosException("No se ha podido insertar el producto " + medicamento,
	 * e); }
	 * 
	 * }
	 */
	@Override
	public Sorteo crearYObtener(Sorteo sorteo) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

			ps.setString(1, right(("00" + sorteo.getNumero_1()), 2));
			ps.setString(2, right(("00" + sorteo.getNumero_2()), 2));
			ps.setString(3, right(("00" + sorteo.getNumero_3()), 2));
			ps.setString(4, right(("00" + sorteo.getNumero_4()), 2));
			ps.setString(5, right(("00" + sorteo.getNumero_5()), 2));

			ps.setString(6, right(("00" + sorteo.getEstrella_1()), 2));
			ps.setString(7, right(("00" + sorteo.getEstrella_2()), 2));

			Date fecha = null;
			if (sorteo.getFechaSorteo() != null) {
				fecha = Date.valueOf(sorteo.getFechaSorteo());
			}

			ps.setDate(8, fecha);

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Se han insertado " + numeroRegistrosInsertados + " registros");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					sorteo.setId(generatedKeys.getLong(1)); // Columna 1
				} else {
					throw new AccesoDatosException("Error al buscar el ID generado de medicamento");
				}
			}

			return sorteo;

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el medicamento " + sorteo, e);
		}
	}

	@Override
	public int numeroRegistros() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_COUNT_ID);) {

			int countRegistros = 0;

			while (rs.next()) {

				countRegistros = rs.getInt(1);
			}

			return countRegistros;

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de sorteos de Euromillones", e);
		}
	}

	/*
	 * /** Mapea un ResultSet a un objeto Medicamento
	 * 
	 * @param rs ResultSet de la consulta SQL
	 * 
	 * @return Medicamento
	 * 
	 * @throws SQLException
	 */
	private Sorteo mapper(ResultSet rs) throws SQLException {

		Sorteo sorteo = new Sorteo();
		Date fecha = null;

		sorteo.setId(rs.getLong("id"));
		sorteo.setNumero_1(rs.getString("numero1"));
		sorteo.setNumero_2(rs.getString("numero2"));
		sorteo.setNumero_3(rs.getString("numero3"));
		sorteo.setNumero_4(rs.getString("numero4"));
		sorteo.setNumero_5(rs.getString("numero5"));
		sorteo.setEstrella_1(rs.getString("estrella1"));
		sorteo.setEstrella_2(rs.getString("estrella2"));

		fecha = rs.getDate("fecha");
		LocalDate fechaSorteo = new java.sql.Date(fecha.getTime()).toLocalDate();

		sorteo.setFechaSorteo(fechaSorteo);

		return sorteo;
	}

	private String right(String value, int length) {
		// To get right characters from a string, change the begin index.
		return value.substring(value.length() - length);
	}
}
