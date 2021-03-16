package com.ipartek.formacion.ejemplofinal.logicanegocio;

import com.ipartek.formacion.ejemplofinal.accesodatos.Dao;
import com.ipartek.formacion.ejemplofinal.accesodatos.DaoFabrica;
import com.ipartek.formacion.ejemplofinal.entidades.Cliente;

import lombok.extern.java.Log;

/**
 * Representa la implentación de la clase ClienteNegocio
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Log
public class ClienteNegocioImpl implements ClienteNegocio {
	private Dao<Cliente> dao = DaoFabrica.getDaoCliente();

	/**
	 * Crear un cliente dentro de la tabla clientes en la BD.BB de supermercado
	 * 
	 * @param cliente cliente a insertar en la BB.DD
	 * @return cliente
	 */
	@Override
	public Cliente altaCliente(Cliente cliente) {
		log.info(cliente.toString());
		return dao.insertar(cliente);
	}

}
