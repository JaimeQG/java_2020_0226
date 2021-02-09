package com.ipartek.formacion.spring.springrestuf2177_3.repositorios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.spring.springrestuf2177_3.entidades.Libro;
import com.ipartek.formacion.spring.springrestuf2177_3.entidades.Resenia;

@Repository
public class LibroDaoJdbc implements Dao<Libro> {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<Libro> obtenerTodos() {
		return jdbc.query(
				"SELECT l.id, l.nombre, CONCAT(a.nombre,' ',a.apellidos) \r\n" + "FROM libros as l \r\n"
						+ "JOIN autores AS a ON a.id = l.autores_id \r\n",
				(rs, rowNum) -> new Libro(rs.getInt("l.id"), rs.getString("l.nombre"),
						rs.getString("CONCAT(a.nombre,' ',a.apellidos)")));
	}

	@Override
	public Libro buscarPorId(int id) {

		return jdbc.queryForObject(
				"SELECT l.id, l.nombre, CONCAT(a.nombre,' ',a.apellidos) \r\n" + "FROM libros as l \r\n"
						+ "JOIN autores AS a ON a.id = l.autores_id \r\n" + "WHERE l.id = ?",
				(rs, rowNum) -> new Libro(rs.getInt("l.id"), rs.getString("l.nombre"),
						rs.getString("CONCAT(a.nombre,' ',a.apellidos)")),
				id);
	}

	public Libro buscarPorIdConResenias(int id) {
		Libro libro = buscarPorId(id);
		List<Resenia> resenias = jdbc.query(
				"SELECT r.id, r.texto, r.libros_id \r\n" + "FROM resenas r WHERE r.libros_id=?",
				(rs, rowNum) -> new Resenia(rs.getInt("id"), rs.getString("texto"), rs.getInt("r.libros_id")), id);

		libro.getResenias().addAll(resenias);

		return libro;
	}

}
