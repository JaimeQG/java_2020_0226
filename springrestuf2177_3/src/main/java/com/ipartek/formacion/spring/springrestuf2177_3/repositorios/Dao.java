package com.ipartek.formacion.spring.springrestuf2177_3.repositorios;

public interface Dao<T> {

	Iterable<T> obtenerTodos();

	T buscarPorId(int id);
}
