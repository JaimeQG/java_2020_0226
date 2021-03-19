package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

/**
 * Representa la lógica del negocio para el plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public interface IntroducirReceta {
	Iterable<Plato> listadoPlatos();

	Plato platoPorId(Long id);

	Plato insertarPlato(Plato plato);

	Plato modificarPlato(Plato plato);

	void borrarPlato(Long id);
}