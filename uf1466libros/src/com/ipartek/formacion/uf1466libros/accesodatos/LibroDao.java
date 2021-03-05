package com.ipartek.formacion.uf1466libros.accesodatos;

import com.ipartek.formacion.uf1466libros.entidades.Libro;

public interface LibroDao extends Dao<Libro> {
	default Libro buscarPorISBN(String ISBN) {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

	default Iterable<Libro> buscarPorTitulo(String titulo) {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}
}
