package com.ipartek.formacion.spring.springMF0226_3.repositorios;

import com.ipartek.formacion.spring.springMF0226_3.entidades.Curso;

public interface CursoDao extends Dao<Curso> {
	Curso obtenerCursoConProfesor(Long id);

	Curso buscarPorIdConResenias(Long id);
}
