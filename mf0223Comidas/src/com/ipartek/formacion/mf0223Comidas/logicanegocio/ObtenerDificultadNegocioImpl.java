package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Dificultad;

import lombok.extern.java.Log;

/**
 * Representa la implentación de la clase ObtenerDificultadNegocio
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Log
public class ObtenerDificultadNegocioImpl implements ObtenerDificultadNegocio {

	private Dao<Dificultad> daoDificultad = DaoFabrica.getDaoDificultad();

	/**
	 * Obtener un listado de todas las dificultades
	 * 
	 * @return Iterable con el conjunto de dificultades
	 */
	@Override
	public Iterable<Dificultad> listadoDificultades() {
		Iterable<Dificultad> dificultades = daoDificultad.obtenerTodos();
		log.info(dificultades.toString());
		return dificultades;
	}

	/**
	 * Obtener una dificultad por su id
	 * 
	 * @param id de la dificultad
	 * @return dificultad
	 */
	@Override
	public Dificultad dificultadPorId(Long id) {
		Dificultad dificultad = daoDificultad.obtenerPorId(id);
		log.info(dificultad.toString());
		return dificultad;
	}

}