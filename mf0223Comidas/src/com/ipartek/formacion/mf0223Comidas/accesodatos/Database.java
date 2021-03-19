package com.ipartek.formacion.mf0223Comidas.accesodatos;

/**
 * Define las operaciones a realizar con la BB.DD. (restore).
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public interface Database {
	default void restoreDatabase(String fileRestore) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

}
