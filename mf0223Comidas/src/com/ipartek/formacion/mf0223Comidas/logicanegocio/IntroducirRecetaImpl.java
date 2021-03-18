package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

import lombok.extern.java.Log;

@Log
public class IntroducirRecetaImpl implements IntroducirReceta {

	private Dao<Plato> daoPlato = DaoFabrica.getDaoPlato();

	@Override
	public Iterable<Plato> listadoPlatos() {
		Iterable<Plato> platos = daoPlato.obtenerTodos();
		log.info(platos.toString());
		return platos;
	}

	@Override
	public Plato platoPorId(Long id) {
		Plato plato = daoPlato.obtenerPorId(id);
		log.info(plato.toString());
		return plato;
	}

	@Override
	public Plato insertarPlato(Plato plato) {
		Plato p = daoPlato.insertar(plato);
		log.info(p.toString());
		return p;
	}

	@Override
	public Plato modificarPlato(Plato plato) {
		Plato p = daoPlato.modificar(plato);
		log.info(p.toString());
		return p;
	}

	@Override
	public void borrarPlato(Long id) {
		daoPlato.borrar(id);
	}

}