package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Dificultad;

public interface ObtenerDificultadNegocio {
	Iterable<Dificultad> listadoDificultades();

	Dificultad dificultadPorId(Long id);

}