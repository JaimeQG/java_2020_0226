package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;

import lombok.extern.java.Log;

@Log
public class ObtenerCategoriaNegocioImpl implements ObtenerCategoriaNegocio {

	private Dao<Categoria> daoCategoria = DaoFabrica.getDaoCategoria();

	@Override
	public Iterable<Categoria> listadoCategorias() {
		Iterable<Categoria> categorias = daoCategoria.obtenerTodos();
		log.info(categorias.toString());
		return categorias;
	}

	@Override
	public Categoria categoriaPorId(Long id) {
		Categoria categoria = daoCategoria.obtenerPorId(id);
		log.info(categoria.toString());
		return categoria;
	}

}