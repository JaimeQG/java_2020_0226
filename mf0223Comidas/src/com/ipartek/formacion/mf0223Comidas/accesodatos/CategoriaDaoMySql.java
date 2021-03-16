package com.ipartek.formacion.mf0223Comidas.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;

public class CategoriaDaoMySql implements Dao<Categoria> {
	private static final String SQL_SELECT = "SELECT c.id AS c_id, c.nombre AS c_nombre FROM categorias AS c";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE c.id = ?";

	@Override
	public Iterable<Categoria> obtenerTodos() {
		try (Connection con = Config.dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Categoria> categorias = new ArrayList<>();

			Categoria categoria;

			while (rs.next()) {
				categoria = new Categoria(rs.getLong("c_id"), rs.getString("c_nombre"));

				categorias.add(categoria);
			}

			return categorias;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos las categorías", e);
		}
	}

	@Override
	public Categoria obtenerPorId(Long id) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Categoria categoria = null;

			if (rs.next()) {
				categoria = new Categoria(rs.getLong("c_id"), rs.getString("c_nombre"));
			}

			return categoria;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener el plato id " + id, e);
		}
	}

}