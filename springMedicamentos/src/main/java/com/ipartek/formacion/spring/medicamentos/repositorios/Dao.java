package com.ipartek.formacion.spring.medicamentos.repositorios;

public interface Dao<T> {
	Iterable<T> obtenerTodos();

	T obtenerPorId(Long id);

	T agregar(T objeto);

	T modificar(T objeto);

	void borrar(Long id);

	int numeroRegistros(String string);

	boolean valorExiste(String columna, String valor);
}
