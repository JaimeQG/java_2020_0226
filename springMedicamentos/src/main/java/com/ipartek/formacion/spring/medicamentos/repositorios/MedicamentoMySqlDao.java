package com.ipartek.formacion.spring.medicamentos.repositorios;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.spring.medicamentos.entidades.Medicamento;

@Repository
public class MedicamentoMySqlDao implements Dao<Medicamento> {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("medicamentos").usingGeneratedKeyColumns("id");
	}

	@Override
	public Iterable<Medicamento> obtenerTodos() {
		return jdbcTemplate.query("SELECT * FROM medicamentos",
				new BeanPropertyRowMapper<Medicamento>(Medicamento.class));
	}

	@Override
	public Medicamento obtenerPorId(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM medicamentos WHERE id = ?",
				new BeanPropertyRowMapper<Medicamento>(Medicamento.class), new Object[] { id });
	}

	@Override
	public Medicamento agregar(Medicamento medicamento) {

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(medicamento);

		Number id = jdbcInsert.executeAndReturnKey(parameters);

		medicamento.setId(id.longValue());

		return medicamento;
	}

	@Override
	public Medicamento modificar(Medicamento medicamento) {
		jdbcTemplate.update("UPDATE medicamentos SET referencia = ?, nombre = ?, precio = ? WHERE id = ?",
				new Object[] { medicamento.getReferencia(), medicamento.getNombre(), medicamento.getPrecio(),
						medicamento.getId() });
		return medicamento;
	}

	@Override
	public void borrar(Long id) {
		jdbcTemplate.update("DELETE FROM medicamentos WHERE id = ?", new Object[] { id });
	}

	@Override
	public int numeroRegistros(String columna) {
		String sql = "SELECT count(" + columna + ") FROM medicamentos";

		int total = jdbcTemplate.queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public boolean valorExiste(String columna, String valor) {

		boolean existe = false;

		String sql = "SELECT count(" + columna + ") FROM medicamentos WHERE ucase(" + columna + ") = ucase('" + valor
				+ "')";

		int total = jdbcTemplate.queryForObject(sql, Integer.class);

		if (total > 0) {
			existe = true;
		}

		return existe;
	}

}
