package com.ipartek.formacion.spring.springMF0226_3.repositorios;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.spring.springMF0226_3.entidades.Alumno;
import com.ipartek.formacion.spring.springMF0226_3.entidades.Curso;
import com.ipartek.formacion.spring.springMF0226_3.entidades.Resenia;

@Repository
public class ReseniaDaoJdbcTemplate implements Dao<Resenia> {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<Resenia> obtenerTodos() {
		// return jdbc.query("SELECT r.codigo, r.comentario, r.fecha, r.curso_codigo
		// FROM resenia r",
		// new BeanPropertyRowMapper<Resenia>(Resenia.class));
		Collection<Resenia> resenias = jdbc.query("SELECT * FROM resenia r  \r\n",
				// "SELECT r.codigo, r.comentario, r.fecha, r.curso_codigo, r.alumno_codigo
				// \r\n" + "FROM resenia r \r\n",
				(rs, rowNum) -> new Resenia(rs.getInt("r.codigo"), rs.getString("r.comentario"),
						new java.sql.Date(rs.getDate("r.fecha").getTime()).toLocalDate(),
						new Curso(rs.getInt("r.curso_codigo")), new Alumno(rs.getInt("r.alumno_codigo"))));

		// "SELECT r.codigo, r.comentario, r.fecha, r.curso_codigo, r.alumno_codigo
		// \r\n" + "FROM resenia r \r\n",
		// (rs, rowNum) -> new Resenia(rs.getInt("r.codigo"),
		// rs.getString("r.comentario"),
		// new java.sql.Date(rs.getDate("r.fecha").getTime()).toLocalDate(),
		// rs.getInt("r.curso_codigo"),
		// rs.getInt("r.alumno_codigo")));

		return resenias;
	}

	@Override
	public Resenia obtenerPorId(Long id) {
		return jdbc.queryForObject("SELECT * FROM resenia WHERE codigo = ?",
				new BeanPropertyRowMapper<Resenia>(Resenia.class), id);
	}

	@Override
	public Resenia insertar(Resenia resenia) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO resenia (comentario, fecha, alumno_codigo, curso_codigo) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			Date fecha = null;
			if (resenia.getFecha() != null) {
				fecha = Date.valueOf(resenia.getFecha());
			}

			ps.setString(1, resenia.getComentario());
			ps.setDate(2, fecha);
			ps.setInt(3, resenia.getAlumno().getCodigo());
			ps.setInt(4, resenia.getCurso().getCodigo());

			return ps;

		}, keyHolder);

		resenia.setCodigo(keyHolder.getKey().intValue());

		return resenia;
	}

	@Override
	public Resenia modificar(Resenia resenia) {
		jdbc.update("UPDATE resenia SET comentario = ?, fecha = ? WHERE codigo = ?", resenia.getComentario(),
				resenia.getFecha(), resenia.getCodigo());
		return resenia;
	}

	@Override
	public void borrar(Long id) {
		jdbc.update("DELETE FROM resenia WHERE codigo = ?", id);
	}

}
