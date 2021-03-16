package com.ipartek.formacion.ejemplofinal.accesodatos;

import com.ipartek.formacion.ejemplofinal.entidades.Cliente;
import com.ipartek.formacion.ejemplofinal.entidades.Producto;

/**
 * DAO Fabrica para las entidades: Producto, Factura, Cliente y Usuario. Separa
 * la capa de acceso a los datos (MySql) de su implementación
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public class DaoFabrica {

	private DaoFabrica() {
	}

	private static final Dao<Producto> daoProducto = new ProductoDaoMySql();
	private static final DaoFactura daoFactura = new FacturaDaoMySql();
	private static final Dao<Cliente> daoCliente = new ClienteDaoMySql();
	private static final DaoUsuario daoUsuario = new UsuarioDaoMySql();

	/**
	 * Obtener objeto de la clase ProductoDaoMySql
	 * 
	 * @return objeto Producto con MySql
	 */
	public static Dao<Producto> getDaoProducto() {
		return daoProducto;
	}

	/**
	 * Obtener objeto de la clase FacturaDaoMySql
	 * 
	 * @return objeto Factura con MySql
	 */
	public static DaoFactura getDaoFactura() {
		return daoFactura;
	}

	/**
	 * Obtener objeto de la clase ClienteDaoMySql
	 * 
	 * @return objeto Cliente con MySql
	 */
	public static Dao<Cliente> getDaoCliente() {
		return daoCliente;
	}

	/**
	 * Obtener objeto de la clase usuarioDaoMySql
	 * 
	 * @return objeto Usuario con MySql
	 */
	public static DaoUsuario getDaoUsuario() {
		return daoUsuario;
	}

}
