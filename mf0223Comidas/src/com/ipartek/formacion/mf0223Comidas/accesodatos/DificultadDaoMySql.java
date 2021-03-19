package com.ipartek.formacion.mf0223Comidas.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.mf0223Comidas.entidades.Dificultad;

/**
 * Implementación con jdbc/mySql del DAO para el objeto Dificultad
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public class DificultadDaoMySql implements Dao<Dificultad> {
	private static final String SQL_SELECT = "SELECT d.id AS d_id, d.nombre AS d_nombre FROM dificultad AS d ORDER BY d.id ASC";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE d.id = ?";

	/**
	 * lista de objetos Dificultad
	 * 
	 * @return todos los objetos Dificultad, si no existe ninguno vacio no null
	 * @throws AccesoDatosException si falla la conexión con la BB.DD.
	 * 
	 */
	@Override
	public Iterable<Dificultad> obtenerTodos() {
		try (Connection con = Config.dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Dificultad> dificultades = new ArrayList<>();

			Dificultad dificultad;

			while (rs.next()) {
				dificultad = new Dificultad(rs.getLong("d_id"), rs.getString("d_nombre"));

				dificultades.add(dificultad);
			}

			return dificultades;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todas las dificultades", e);
		}
	}

	/**
	 * Recupera el detalle de un objeto Dificultad
	 * 
	 * @param id identificador
	 * @throws AccesoDatosException si falla la conexión con la BB.DD
	 * @return Objeto Dificultad con sus datos o null si no encuentra por su id
	 */
	@Override
	public Dificultad obtenerPorId(Long id) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Dificultad dificultad = null;

			if (rs.next()) {
				dificultad = new Dificultad(rs.getLong("d_id"), rs.getString("d_nombre"));
			}

			return dificultad;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener la dificultad id " + id, e);
		}
	}

}