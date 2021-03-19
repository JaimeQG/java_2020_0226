package com.ipartek.formacion.mf0223Comidas.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.mf0223Comidas.entidades.Origen;

/**
 * Implementación con jdbc/mySql del DAO para el objeto Origen
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public class OrigenDaoMySql implements Dao<Origen> {
	private static final String SQL_SELECT = "SELECT o.id AS o_id, o.nombre AS o_nombre FROM origen AS o ORDER BY o.nombre ASC";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE o.id = ?";

	/**
	 * lista de objetos Origen
	 * 
	 * @return todos los Orígenes, si no existe ninguno vacio no null
	 * @throws AccesoDatosException si falla la conexión con la BB.DD.
	 * 
	 */
	@Override
	public Iterable<Origen> obtenerTodos() {
		try (Connection con = Config.dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Origen> origenes = new ArrayList<>();

			Origen origen;

			while (rs.next()) {
				origen = new Origen(rs.getLong("o_id"), rs.getString("o_nombre"));

				origenes.add(origen);
			}

			return origenes;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los origenes", e);
		}
	}

	/**
	 * Recupera el detalle de un Plato
	 * 
	 * @param id identificador
	 * @throws AccesoDatosException si falla la conexión con la BB.DD
	 * @return Origen con sus datos o null si no encuentra por su id
	 */
	@Override
	public Origen obtenerPorId(Long id) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Origen origen = null;

			if (rs.next()) {
				origen = new Origen(rs.getLong("o_id"), rs.getString("o_nombre"));
			}

			return origen;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener el origen id " + id, e);
		}
	}

}