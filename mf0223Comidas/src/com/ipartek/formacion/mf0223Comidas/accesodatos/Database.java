package com.ipartek.formacion.mf0223Comidas.accesodatos;

public interface Database {
	default void restoreDatabase(String fileRestore) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

}
