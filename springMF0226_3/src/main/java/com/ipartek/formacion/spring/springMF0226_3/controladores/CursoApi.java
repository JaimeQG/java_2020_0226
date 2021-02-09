package com.ipartek.formacion.spring.springMF0226_3.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.spring.springMF0226_3.entidades.Curso;
import com.ipartek.formacion.spring.springMF0226_3.repositorios.CursoDao;

@RestController
@RequestMapping("/api/cursos")
public class CursoApi {

	private static final Logger LOGGER = Logger.getLogger(CursoApi.class.getName());

	@Autowired
	private CursoDao dao;

	@GetMapping
	public Iterable<Curso> get() {
		return dao.obtenerTodos();
	}

	@GetMapping("{id}")
	public ResponseEntity<Curso> getPorId(@PathVariable Long id) {
		Curso curso = dao.buscarPorIdConResenias(id);

		if (curso == null) {
			return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		}

		LOGGER.log(Level.INFO, curso.toString());

		return new ResponseEntity<Curso>(curso, HttpStatus.OK);

	}
}
