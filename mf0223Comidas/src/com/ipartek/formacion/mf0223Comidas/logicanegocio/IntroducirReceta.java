package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

public interface IntroducirReceta {
	Iterable<Plato> listadoPlatos();

	Plato platoPorId(Long id);

	Plato insertarPlato(Plato plato);

	Plato modificarPlato(Plato plato);

	void borrarPlato(Long id);
}