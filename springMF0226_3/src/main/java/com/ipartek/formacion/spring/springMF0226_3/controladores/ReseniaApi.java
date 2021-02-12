package com.ipartek.formacion.spring.springMF0226_3.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.spring.springMF0226_3.entidades.Resenia;
import com.ipartek.formacion.spring.springMF0226_3.repositorios.Dao;

@RestController
@RequestMapping("/api/resenias")
public class ReseniaApi {

	private static final Logger LOGGER = Logger.getLogger(ReseniaApi.class.getName());

	@Autowired
	private Dao<Resenia> dao;

	@GetMapping
	public Iterable<Resenia> get() {
		return dao.obtenerTodos();
	}

	@GetMapping("{id}")
	public ResponseEntity<Resenia> getPorId(@PathVariable Long id) {
		Resenia resenia = dao.obtenerPorId(id);

		if (resenia == null) {
			return new ResponseEntity<Resenia>(HttpStatus.NOT_FOUND);
		}

		LOGGER.log(Level.INFO, resenia.toString());
		return new ResponseEntity<Resenia>(resenia, HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Resenia post(@RequestBody Resenia resenia) {

		LOGGER.log(Level.INFO, resenia.toString());
		return dao.insertar(resenia);
	}

	@PutMapping("{id}")
	public ResponseEntity<Resenia> put(@PathVariable Long id, @RequestBody Resenia resenia) {
		if (id != resenia.getCodigo()) {
			LOGGER.log(Level.INFO, "resenia id " + id + "!= " + resenia.toString());
			return new ResponseEntity<Resenia>(HttpStatus.BAD_REQUEST);
		}

		Resenia reseniaExiste = null;

		try {
			reseniaExiste = dao.obtenerPorId(id);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "resenia id " + id + " no existe en la Tabla resenia");
		}

		if (reseniaExiste == null) {
			return new ResponseEntity<Resenia>(HttpStatus.NOT_FOUND);
		}

		LOGGER.log(Level.INFO, resenia.toString());
		return new ResponseEntity<Resenia>(dao.modificar(resenia), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Resenia> delete(@PathVariable Long id) {
		try {
			dao.borrar(id);
			return new ResponseEntity<Resenia>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Resenia>(HttpStatus.NOT_FOUND);
		}
	}
}
