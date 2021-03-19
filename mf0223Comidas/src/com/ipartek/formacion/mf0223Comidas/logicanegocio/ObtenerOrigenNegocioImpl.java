package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Origen;

import lombok.extern.java.Log;

/**
 * Representa la implentación de la clase ObtenerOrigenNegocio
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Log
public class ObtenerOrigenNegocioImpl implements ObtenerOrigenNegocio {

	private Dao<Origen> daoOrigen = DaoFabrica.getDaoOrigen();

	/**
	 * Obtener un listado de todos los origenes
	 * 
	 * @return Iterable con el conjunto de origenes
	 */
	@Override
	public Iterable<Origen> listadoOrigenes() {
		Iterable<Origen> origenes = daoOrigen.obtenerTodos();
		log.info(origenes.toString());
		return origenes;
	}

	/**
	 * Obtener un origen por su id
	 * 
	 * @param id del origen
	 * @return origen
	 */
	@Override
	public Origen origenPorId(Long id) {
		Origen origen = daoOrigen.obtenerPorId(id);
		log.info(origen.toString());
		return origen;
	}

}