package com.ipartek.formacion.ejemplofinal.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.ipartek.formacion.ejemplofinal.entidades.DetalleFactura;
import com.ipartek.formacion.ejemplofinal.entidades.Factura;

class FacturaDaoMySql implements DaoFactura {

	private static final String SQL_INSERT = "INSERT INTO facturas (clientes_id, codigo, fecha) VALUES (?,?,?)";
	private static final String SQL_INSERT_DETALLE = "INSERT INTO facturas_has_productos (facturas_id, productos_id, cantidad) VALUES (?,?,?)";
	private static final String SQL_CODIGO = "SELECT MAX(codigo) FROM supermercado.facturas WHERE codigo LIKE CONCAT(?, '____')";

	/**
	 * Crea una nueva Factura y cuando es guardado se le asigan un nuevo id
	 * 
	 * @param Factura con los datos a guardar
	 * @throws AccesoDatosException si falla la conexión con la BB.DD
	 * @return Factura con su id actualizado en la llama a la función privada
	 *         insertarImpl(Factura factura, Connection con)
	 */
	@Override
	public Factura insertar(Factura factura) {
		try (Connection con = Config.dataSource.getConnection()) {
			con.setAutoCommit(false);

			return insertarImpl(factura, con);
		} catch (Exception e) {
			throw new AccesoDatosException("Error al insertar la factura " + factura, e);
		}
	}

	private Factura insertarImpl(Factura factura, Connection con) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
			ps.setLong(1, factura.getCliente().getId());
			ps.setString(2, factura.getCodigo());
			ps.setObject(3, factura.getFecha());

			int num = ps.executeUpdate();

			if (num != 1) {
				throw new AccesoDatosException("Ha habido una incidencia en la inserción de factura: " + num);
			}

			ResultSet rs = ps.getGeneratedKeys();

			rs.next();

			factura.setId(rs.getLong(1));

			try (PreparedStatement pst = con.prepareStatement(SQL_INSERT_DETALLE, Statement.RETURN_GENERATED_KEYS)) {

				pst.setLong(1, factura.getId());

				for (DetalleFactura detalle : factura.getDetallesFactura()) {
					pst.setLong(2, detalle.getProducto().getId());
					pst.setInt(3, detalle.getCantidad());

					num = pst.executeUpdate();

					if (num != 1) {
						throw new AccesoDatosException(
								"Ha habido una incidencia en la inserción de un detalle de factura " + detalle);
					}
				}
			}

			con.commit();

			return factura;
		} catch (Exception e) {
			con.rollback();
			throw new AccesoDatosException("Se ha hecho rollback", e);
		}
	}

	/**
	 * Obtiene el último código de factura de la tabla facturas de la BB.DD.
	 * supermercado
	 * 
	 * @param
	 * @throws Exception si falla la conexión con la BB.DD
	 * @return Último código de la tabla facturas
	 */
	@Override
	public String obtenerUltimoCodigo() {
		try (Connection con = Config.dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_CODIGO);) {
			ps.setString(1, String.valueOf(LocalDate.now().getYear()));
			ResultSet rs = ps.executeQuery();
			rs.next();

			return rs.getString(1);
		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido obtener el código", e);
		}
	}

}