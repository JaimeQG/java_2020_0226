package com.ipartek.formacion.spring.springMF0226_3.repositorios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.spring.springMF0226_3.entidades.Alumno;
import com.ipartek.formacion.spring.springMF0226_3.entidades.Curso;
import com.ipartek.formacion.spring.springMF0226_3.entidades.Profesor;
import com.ipartek.formacion.spring.springMF0226_3.entidades.Resenia;

@Repository
public class CursoDaoJdbcTemplate implements CursoDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<Curso> obtenerTodos() {
		return jdbc.query(
				"SELECT c.codigo, c.nombre, c.identificador, c.nHoras, p.codigo, p.nombre, p.apellidos \r\n"
						+ "FROM curso c\r\n" + "LEFT JOIN profesor p ON c.profesor_codigo = p.codigo\r\n",
				(rs, rowNum) -> new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
						rs.getString("c.identificador"), rs.getInt("c.nHoras"),
						new Profesor(rs.getInt("p.codigo"), rs.getString("p.nombre"), rs.getString("p.apellidos"))));
	}

	@Override
	public Curso obtenerPorId(Long id) {
		return jdbc.queryForObject("SELECT * FROM curso c WHERE c.codigo = ?",
				new BeanPropertyRowMapper<Curso>(Curso.class), id);
	}

	@Override
	public Curso insertar(Curso curso) {
		throw new RuntimeException("INSERTAR POR IMPLEMENTAR");
	}

	@Override
	public Curso modificar(Curso curso) {
		throw new RuntimeException("MODIFICAR POR IMPLEMENTAR");
	}

	@Override
	public void borrar(Long id) {
		throw new RuntimeException("BORRAR POR IMPLEMENTAR");
	}

	@Override
	public Curso obtenerCursoConProfesor(Long id) {
		Collection<Curso> cursos = jdbc
				.query("SELECT c.codigo, c.nombre, c.identificador, c.nHoras, p.codigo, p.nombre, p.apellidos \r\n"
						+ "FROM curso c \r\n" + "LEFT JOIN profesor p ON c.profesor_codigo = p.codigo \r\n"
						+ "WHERE c.codigo = ?",
						(rs, rowNum) -> new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
								rs.getString("c.identificador"), rs.getInt("c.nHoras"), new Profesor(
										rs.getInt("p.codigo"), rs.getString("p.nombre"), rs.getString("p.apellidos"))),
						id);

		Curso curso = null;
		if (!cursos.isEmpty()) {
			curso = cursos.iterator().next();
		}
		return curso;
	}

	@Override
	public Curso buscarPorIdConResenias(Long id) {
		Curso curso = obtenerCursoConProfesor(id);

		if (curso == null) {
			return curso;
		}

		Collection<Resenia> resenias = jdbc.query(
				"SELECT r.codigo, r.comentario, r.fecha, c.codigo, c.nombre, c.identificador, c.nHoras, a.codigo, a.nombre, a.apellidos  \r\n"
						+ "FROM resenia r \r\n" + "LEFT JOIN curso c ON c.codigo = r.curso_codigo\r\n"
						+ "LEFT JOIN alumno a ON a.codigo = r.alumno_codigo \r\n" + "WHERE c.codigo = ?\r\n"
						+ "ORDER BY r.codigo",
				(rs, rowNum) -> new Resenia(rs.getInt("r.codigo"), rs.getString("r.comentario"),
						new java.sql.Date(rs.getDate("r.fecha").getTime()).toLocalDate(),
						new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"), rs.getString("c.identificador"),
								rs.getInt("c.nHoras")),
						new Alumno(rs.getInt("a.codigo"), rs.getString("a.nombre"), rs.getString("a.apellidos"))),
				id);

		for (Resenia resenia : resenias) {
			System.out.println(resenia.toString());
		}

		curso.getResenias().addAll(resenias);
		return curso;
	}

}