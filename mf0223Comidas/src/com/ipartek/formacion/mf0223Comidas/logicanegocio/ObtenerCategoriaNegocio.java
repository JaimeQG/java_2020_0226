package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;

public interface ObtenerCategoriaNegocio {
	Iterable<Categoria> listadoCategorias();

	Categoria categoriaPorId(Long id);

}