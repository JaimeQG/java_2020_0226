package com.ipartek.formacion.uf1465.accesodatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ipartek.formacion.uf1465.entidades.Categoria;
import com.ipartek.formacion.uf1465.entidades.Producto;

public class ProductoDaoMySql implements Dao<Producto> {

	private static final String SQL_SELECT = "SELECT p.id p_id, p.nombre p_nombre, p.precio p_precio, c.id c_id, c.nombre c_nombre FROM productos AS P LEFT JOIN categorias as C ON P.categorias_id = C.id";

	private DataSource dataSource = null;

	public ProductoDaoMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/uf1465");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el JNDI de usuarioservlets", e);
		}
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		try (Connection con = dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SQL_SELECT)) {
			ArrayList<Producto> productos = new ArrayList<>();
			Producto producto;
			Categoria categoria;

			while (rs.next()) {
				categoria = new Categoria(rs.getLong("c_id"), rs.getString("c_nombre"));
				producto = new Producto(rs.getLong("p_id"), rs.getString("p_nombre"), rs.getBigDecimal("p_precio"),
						categoria);

				productos.add(producto);
			}

			return productos;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se han podido obtener todos los registros de usuarios");
		} catch (Exception e) {
			throw new AccesoDatosException(
					"ERROR NO ESPERADO: No se han podido obtener todos los registros de usuarios");
		}
	}

}
