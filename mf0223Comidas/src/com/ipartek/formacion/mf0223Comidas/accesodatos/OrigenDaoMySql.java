package com.ipartek.formacion.mf0223Comidas.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.mf0223Comidas.entidades.Origen;

public class OrigenDaoMySql implements Dao<Origen> {
	private static final String SQL_SELECT = "SELECT o.id AS o_id, o.nombre AS o_nombre FROM origen AS o ORDER BY o.nombre ASC";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE o.id = ?";

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