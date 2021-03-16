package com.ipartek.formacion.ejemplofinal.logicanegocio;

/**
 * Fabrica Negocio para las clases de negocio: CarritoNegocio, ClienteNegocio y
 * UsuarioNegocio
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public class FabricaNegocio {

	private FabricaNegocio() {
	}

	private static final CarritoNegocio carritoNegocio = new CarritoNegocioImpl();
	private static final ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	private static final UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

	/**
	 * Obtener objeto de la clase CarritoNegocio
	 * 
	 * @return objeto CarritoNegocio
	 */
	public static CarritoNegocio getCarritoNegocio() {
		return carritoNegocio;
	}

	/**
	 * Obtener objeto de la clase ClienteNegocio
	 * 
	 * @return objeto CarritoNegocio
	 */
	public static ClienteNegocio getClienteNegocio() {
		return clienteNegocio;
	}

	/**
	 * Obtener objeto de la clase UsuarioNegocio
	 * 
	 * @return objeto UsuarioNegocio
	 */
	public static UsuarioNegocio getUsuarioNegocio() {
		return usuarioNegocio;
	}
}
