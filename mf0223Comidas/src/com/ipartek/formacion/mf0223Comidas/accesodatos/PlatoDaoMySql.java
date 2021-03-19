package com.ipartek.formacion.mf0223Comidas.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;
import com.ipartek.formacion.mf0223Comidas.entidades.Dificultad;
import com.ipartek.formacion.mf0223Comidas.entidades.Origen;
import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

/**
 * Implementación con jdbc/mySql del DAO para el objeto Plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public class PlatoDaoMySql implements Dao<Plato> {
	private static final String SQL_SELECT = "SELECT p.id AS id, p.nombre AS nombre, p.preparacion AS preparacion, p.tiempo AS tiempo, p.calorias AS calorias, c.id AS c_id, c.nombre AS c_nombre, o.id AS o_id, o.nombre AS o_nombre, d.id AS d_id, d.nombre AS d_nombre \r\n"
			+ "FROM platos p \r\n" + "JOIN categorias c ON p.categorias_id = c.id \r\n"
			+ "JOIN origen o ON p.origen_id = o.id \r\n" + "JOIN dificultad d ON p.dificultad_id = d.id ";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE p.id = ?";
	private static final String SQL_INSERT = "INSERT INTO platos (nombre, preparacion, tiempo, calorias, categorias_id, origen_id, dificultad_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE platos SET nombre=?, preparacion=?, tiempo=?, calorias=?, categorias_id=?, origen_id=?, dificultad_id=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM platos AS p WHERE p.id = ?";

	/**
	 * lista de objetos Plato
	 * 
	 * @return todos los Platos, si no existe ninguno vacio no null
	 * @throws AccesoDatosException si falla la conexión con la BB.DD.
	 * 
	 */
	@Override
	public Iterable<Plato> obtenerTodos() {
		try (Connection con = Config.dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT + " ORDER BY p.id ASC")) {
			ArrayList<Plato> platos = new ArrayList<>();

			Plato plato;
			Categoria categoria;
			Origen origen;
			Dificultad dificultad;

			while (rs.next()) {
				categoria = new Categoria(rs.getLong("c_id"), rs.getString("c_nombre"));
				origen = new Origen(rs.getLong("o_id"), rs.getString("o_nombre"));
				dificultad = new Dificultad(rs.getLong("d_id"), rs.getString("d_nombre"));

				plato = new Plato(rs.getLong("id"), rs.getString("nombre"), rs.getString("preparacion"),
						rs.getInt("tiempo"), rs.getInt("calorias"), categoria, origen, dificultad);

				platos.add(plato);
			}

			return platos;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener todos los platos", e);
		}
	}

	/**
	 * Recupera el detalle de un Plato
	 * 
	 * @param id identificador
	 * @throws AccesoDatosException si falla la conexión con la BB.DD
	 * @return Plato con sus datos o null si no encuentra por su id
	 */
	@Override
	public Plato obtenerPorId(Long id) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			Plato plato = null;
			Categoria categoria;
			Origen origen;
			Dificultad dificultad;

			if (rs.next()) {
				categoria = new Categoria(rs.getLong("c_id"), rs.getString("c_nombre"));
				origen = new Origen(rs.getLong("o_id"), rs.getString("o_nombre"));
				dificultad = new Dificultad(rs.getLong("d_id"), rs.getString("d_nombre"));

				plato = new Plato(rs.getLong("id"), rs.getString("nombre"), rs.getString("preparacion"),
						rs.getInt("tiempo"), rs.getInt("calorias"), categoria, origen, dificultad);
			}

			return plato;
		} catch (Exception e) {
			throw new AccesoDatosException("Error al obtener el plato id " + id, e);
		}
	}

	/**
	 * Crea un nuevo Plato y cuando es guardado se le asigan un nuevo id
	 * 
	 * @param plato con los datos a guardar
	 * @throws AccesoDatosException si falla la conexión con la BB.DD
	 * @return objeto Plato con su id actualizado
	 * 
	 */
	@Override
	public Plato insertar(Plato plato) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT);) {

			pst.setString(1, plato.getNombre());
			pst.setString(2, plato.getPreparacion());
			pst.setInt(3, plato.getTiempo());
			pst.setInt(4, plato.getCalorias());
			pst.setLong(5, plato.getCategoria().getId());
			pst.setLong(6, plato.getOrigen().getId());
			pst.setLong(7, plato.getDificultad().getId());

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

	/**
	 * Modifica un Plato
	 * 
	 * @param plato con los datos modificados a guardar
	 * @throws AccesoDatosException si falla la conexión con la BB.DD
	 * @return objeto Plato con sus datos modificados
	 * 
	 */
	@Override
	public Plato modificar(Plato plato) {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {

			pst.setString(1, plato.getNombre());
			pst.setString(2, plato.getPreparacion());
			pst.setInt(3, plato.getTiempo());
			pst.setInt(4, plato.getCalorias());
			pst.setLong(5, plato.getCategoria().getId());
			pst.setLong(6, plato.getOrigen().getId());
			pst.setLong(7, plato.getDificultad().getId());

			pst.setLong(8, plato.getId());

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

	/**
	 * Borrar un Plato
	 * 
	 * @param id del Plato
	 * @throws AccesoDatosException si falla la conexión con la BB.DD
	 * 
	 */
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