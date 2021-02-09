package com.ipartek.formacion.spring.springrestuf2177_3.repositorios;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.spring.springrestuf2177_3.entidades.Resenia;

@Repository
public class ReseniaDaoJdbc implements ReseniaDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Resenia insertar(Resenia resenia) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO resenas (texto, libros_id) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, resenia.getTexto());
			ps.setInt(2, resenia.getLibros_id());
			return ps;
		}, keyHolder);

		resenia.setId(keyHolder.getKey().intValue());

		return resenia;
	}

	@Override
	public Iterable<Resenia> obtenerTodos() {
		return jdbc.query("SELECT * FROM resenias", new BeanPropertyRowMapper<Resenia>(Resenia.class));
	}

	@Override
	public Resenia buscarPorId(int id) {
		return jdbc.queryForObject("SELECT * FROM resenias WHERE codigo = ?",
				new BeanPropertyRowMapper<Resenia>(Resenia.class), id);
	}

}
