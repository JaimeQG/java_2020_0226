package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

public interface PlatoNegocio {
	Iterable<Plato> listadoPlatos();

	Plato platoPorId(Long id);

	Plato insertarPlato(Plato plato);

	Plato modificarPlato(Plato plato);

	void borrarPlato(Long id);
}