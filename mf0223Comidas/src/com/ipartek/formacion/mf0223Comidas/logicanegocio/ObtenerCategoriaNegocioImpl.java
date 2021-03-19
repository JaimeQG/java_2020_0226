package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;

import lombok.extern.java.Log;

/**
 * Representa la implentación de la clase ObtenerCategoriaNegocio
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Log
public class ObtenerCategoriaNegocioImpl implements ObtenerCategoriaNegocio {

	private Dao<Categoria> daoCategoria = DaoFabrica.getDaoCategoria();

	/**
	 * Obtener un listado de todas las categorias
	 * 
	 * @return Iterable con el conjunto de categorias
	 */
	@Override
	public Iterable<Categoria> listadoCategorias() {
		Iterable<Categoria> categorias = daoCategoria.obtenerTodos();
		log.info(categorias.toString());
		return categorias;
	}

	/**
	 * Obtener una categoria por su id
	 * 
	 * @param id de la categoria
	 * @return categoria
	 */
	@Override
	public Categoria categoriaPorId(Long id) {
		Categoria categoria = daoCategoria.obtenerPorId(id);
		log.info(categoria.toString());
		return categoria;
	}

}