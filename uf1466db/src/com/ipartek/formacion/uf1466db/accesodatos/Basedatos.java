package com.ipartek.formacion.uf1466db.accesodatos;

import java.util.ArrayList;
import java.util.TreeMap;

public interface Basedatos {

	default TreeMap<Integer, String> obtenerBaseDatos(String basedatos) {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

	default ArrayList<String> obtenerTablas(String basedatos) {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

	default boolean borrarBaseDatos(String dbOrigen, String dbDestino) {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

	default boolean crearBaseDatos(String dbOrigen, String dbDestino) {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

}
