package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Origen;

import lombok.extern.java.Log;

@Log
public class OrigenNegocioImpl implements OrigenNegocio {

	private Dao<Origen> daoOrigen = DaoFabrica.getDaoOrigen();

	@Override
	public Iterable<Origen> listadoOrigenes() {
		Iterable<Origen> origenes = daoOrigen.obtenerTodos();
		log.info(origenes.toString());
		return origenes;
	}

	@Override
	public Origen origenPorId(Long id) {
		Origen origen = daoOrigen.obtenerPorId(id);
		log.info(origen.toString());
		return origen;
	}

}