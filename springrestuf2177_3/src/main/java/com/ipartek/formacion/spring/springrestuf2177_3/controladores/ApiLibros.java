package com.ipartek.formacion.spring.springrestuf2177_3.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.spring.springrestuf2177_3.entidades.Libro;
import com.ipartek.formacion.spring.springrestuf2177_3.entidades.Resenia;
import com.ipartek.formacion.spring.springrestuf2177_3.repositorios.LibroDaoJdbc;
import com.ipartek.formacion.spring.springrestuf2177_3.repositorios.ReseniaDao;

@RestController
@RequestMapping("/api/libros")
public class ApiLibros {

	private static final Logger LOGGER = Logger.getLogger(ApiLibros.class.getName());

	@Autowired
	private LibroDaoJdbc daoLibro;
	@Autowired
	private ReseniaDao daoResenia;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Resenia post(@RequestBody Resenia resenia) {
		LOGGER.log(Level.INFO, "POST: Crear Resena " + resenia.toString());

		return daoResenia.insertar(resenia);
	}

	@GetMapping
	public Iterable<Libro> get() {
		LOGGER.log(Level.INFO, "GET: Lista de libros ");
		return daoLibro.obtenerTodos();
	}

	@GetMapping("{id}/resenas")
	public ResponseEntity<Libro> getPorId(@PathVariable int id) {
		LOGGER.log(Level.INFO, "GET: " + id + "/resenas");

		Libro libro = daoLibro.buscarPorIdConResenias(id);

		if (libro == null) {
			LOGGER.log(Level.WARNING, "Libro id " + id + " no existe en la Tabla libros");
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}

		LOGGER.log(Level.INFO, libro.toString());

		return new ResponseEntity<Libro>(libro, HttpStatus.OK);

	}

}