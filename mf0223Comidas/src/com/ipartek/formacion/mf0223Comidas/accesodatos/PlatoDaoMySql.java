package com.ipartek.formacion.mf0223Comidas.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;
import com.ipartek.formacion.mf0223Comidas.entidades.Origen;
import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

public class PlatoDaoMySql implements Dao<Plato> {
	private static final String SQL_SELECT = "SELECT p.id AS id, p.nombre AS nombre, p.calorias AS calorias, c.id AS c_id, c.nombre AS c_nombre, o.id AS o_id, o.nombre AS o_nombre\r\n"
			+ "FROM platos p \r\n" + "JOIN categorias c ON p.categorias_id = c.id\r\n"
			+ "JOIN origen o ON p.origen_id = o.id";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE p.id = ?";
	private static final String SQL_INSERT = "INSERT INTO platos (nombre, calorias, categorias_id, origen_id) VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE platos SET nombre=?, calorias=?, categorias_id=?, origen_id=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM platos AS p WHERE p.id = ?";

	@Override
	public Iterable<Plato> obtenerTodos() {
		try (Connection con = Config.dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT + " ORDER BY p.id ASC")) {
			ArrayList<Plato> platos = new ArrayList<>();

			Plato plato;
			Categoria categoria;
			Origen origen;

			while (rs.next()) {
				categoria = new Categoria(rs.getLong("c_id"), rs.getString("c_nombre"));
				origen = new Origen(rs.getLong("o_id"), rs.getString("o_nombre"));

				plato = new Plato(rs.getLong("id"), rs.getString("nombre"), rs.getInt("calorias"), categoria, origen);

				platos.add(plato);
			}

			return platos;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los platos", e);
		}
	}

	@Override
	public Plato obtenerPorId(Long id) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Plato plato = null;
			Categoria categoria;
			Origen origen;

			if (rs.next()) {
				categoria = new Categoria(rs.getLong("c_id"), rs.getString("c_nombre"));
				origen = new Origen(rs.getLong("o_id"), rs.getString("o_nombre"));

				plato = new Plato(rs.getLong("id"), rs.getString("nombre"), rs.getInt("calorias"), categoria, origen);
			}

			return plato;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener el plato id " + id, e);
		}
	}

	@Override
	public Plato insertar(Plato plato) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT);) {

			pst.setString(1, plato.getNombre());
			pst.setInt(2, plato.getCalorias());
			pst.setLong(3, plato.getCategoria().getId());
			pst.setLong(4, plato.getOrigen().getId());

			if (pst.executeUpdate() != 1) {
				throw new AccesoDatosException("No se ha insertado el plato: " + plato);
			}

			return plato;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el plato: " + plato, e);
		} catch (Exception e) {
			throw new AccesoDatosException("ERROR NO ESPERADO: No se ha podido insertar el plato: " + plato, e);
		}
	}

	@Override
	public Plato modificar(Plato plato) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {

			pst.setString(1, plato.getNombre());
			pst.setInt(2, plato.getCalorias());
			pst.setLong(3, plato.getCategoria().getId());
			pst.setLong(4, plato.getOrigen().getId());

			pst.setLong(5, plato.getId());

			if (pst.executeUpdate() != 1) {
				throw new AccesoDatosException("No se ha modificado el plato: " + plato);
			}

			return plato;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificado el plato: " + plato, e);
		} catch (Exception e) {
			throw new AccesoDatosException("ERROR NO ESPERADO: No se ha podido modificado el plato: " + plato, e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);) {

			pst.setLong(1, id);

			int numeroRegistrosBorrados = pst.executeUpdate();

			if (numeroRegistrosBorrados != 1) {
				throw new AccesoDatosException("Se han borrado " + numeroRegistrosBorrados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar el plato " + id, e);
		}
	}
}