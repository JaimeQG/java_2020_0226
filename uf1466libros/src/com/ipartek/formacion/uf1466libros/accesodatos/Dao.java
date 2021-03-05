package com.ipartek.formacion.uf1466libros.accesodatos;

public interface Dao<T> {
	default Iterable<T> obtenerTodos() {
		throw new AccesoDatosException("METODO NO IMPLEMENTADPO");
	}

	default T obtenerPorId(Long id) {
		throw new AccesoDatosException("METODO NO IMPLEMENTADPO");
	}

	default T insertar(T objeto) {
		throw new AccesoDatosException("METODO NO IMPLEMENTADPO");
	}

	default T modificar(T objeto) {
		throw new AccesoDatosException("METODO NO IMPLEMENTADPO");
	}

	default void borrar(Long id) {
		throw new AccesoDatosException("METODO NO IMPLEMENTADPO");
	}

}
