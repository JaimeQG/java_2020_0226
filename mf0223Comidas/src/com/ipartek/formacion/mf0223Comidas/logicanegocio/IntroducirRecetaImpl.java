package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.Dao;
import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

import lombok.extern.java.Log;

/**
 * Representa la implentación de la clase IntroducirReceta
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Log
public class IntroducirRecetaImpl implements IntroducirReceta {

	private Dao<Plato> daoPlato = DaoFabrica.getDaoPlato();

	/**
	 * Obtener un listado de todos los platos
	 * 
	 * @return Iterable con el conjunto de platos
	 */
	@Override
	public Iterable<Plato> listadoPlatos() {
		Iterable<Plato> platos = daoPlato.obtenerTodos();
		log.info(platos.toString());
		return platos;
	}

	/**
	 * Obtener un plato por su id
	 * 
	 * @param id del plato
	 * @return plato
	 */
	@Override
	public Plato platoPorId(Long id) {
		Plato plato = daoPlato.obtenerPorId(id);
		log.info(plato.toString());
		return plato;
	}

	/**
	 * Insertar un plato nuevo
	 * 
	 * @param plato nuevo a insertar
	 * @return plato
	 */
	@Override
	public Plato insertarPlato(Plato plato) {
		Plato p = daoPlato.insertar(plato);
		log.info(p.toString());
		return p;
	}

	/**
	 * Modifica un plato
	 * 
	 * @param plato con los datos a modificar
	 * @return plato
	 */
	@Override
	public Plato modificarPlato(Plato plato) {
		Plato p = daoPlato.modificar(plato);
		log.info(p.toString());
		return p;
	}

	/**
	 * Borra un plato por su id
	 * 
	 * @param id del plato
	 */
	@Override
	public void borrarPlato(Long id) {
		daoPlato.borrar(id);
	}

}