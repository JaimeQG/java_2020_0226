package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;

/**
 * Representa la lógica del negocio para la Categoría del plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public interface ObtenerCategoriaNegocio {
	Iterable<Categoria> listadoCategorias();

	Categoria categoriaPorId(Long id);

}