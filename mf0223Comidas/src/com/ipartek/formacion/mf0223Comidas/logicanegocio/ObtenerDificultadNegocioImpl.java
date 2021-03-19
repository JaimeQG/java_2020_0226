package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Dificultad;

import lombok.extern.java.Log;

@Log
public class ObtenerDificultadNegocioImpl implements ObtenerDificultadNegocio {

	private Dao<Dificultad> daoDificultad = DaoFabrica.getDaoDificultad();

	@Override
	public Iterable<Dificultad> listadoDificultades() {
		Iterable<Dificultad> dificultades = daoDificultad.obtenerTodos();
		log.info(dificultades.toString());
		return dificultades;
	}

	@Override
	public Dificultad dificultadPorId(Long id) {
		Dificultad dificultad = daoDificultad.obtenerPorId(id);
		log.info(dificultad.toString());
		return dificultad;
	}

}