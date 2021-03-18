package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;

public interface CategoriaNegocio {
	Iterable<Categoria> listadoCategorias();

	Categoria categoriaPorId(Long id);

}