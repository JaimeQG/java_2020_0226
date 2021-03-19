package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Dificultad;

/**
 * Representa la lógica del negocio para la Dificultad del plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public interface ObtenerDificultadNegocio {
	Iterable<Dificultad> listadoDificultades();

	Dificultad dificultadPorId(Long id);

}