package com.ipartek.formacion.spring.springjdbc.repositorios;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.spring.springjdbc.entidades.Cliente;

@Repository
public class ClienteMySqlDao implements Dao<Cliente> {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("clientes").usingGeneratedKeyColumns("id");
	}

	@Override
	public Iterable<Cliente> obtenerTodos() {
		return jdbcTemplate.query("SELECT * FROM clientes", new BeanPropertyRowMapper<Cliente>(Cliente.class));
	}

	@Override
	public Cliente obtenerPorId(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM clientes WHERE id = ?",
				new BeanPropertyRowMapper<Cliente>(Cliente.class), new Object[] { id });
	}

	@Override
	public Cliente agregar(Cliente cliente) {

		// simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

		// jdbcTemplate.update(
		// "INSERT INTO clientes (nombre, apellidos, cif, fecha_nacimiento) VALUES (?,
		// ?, ?, ?)", new Object[] {
		// cliente.getNombre(), cliente.getApellidos(), cliente.getCif(),
		// cliente.getFechaNacimiento() },
		// keyHolder);

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(cliente);

		Number id = jdbcInsert.executeAndReturnKey(parameters);

		System.out.println("id " + id.longValue());
		cliente.setId(id.longValue());

		return cliente;
	}

	@Override
	public Cliente modificar(Cliente cliente) {
		jdbcTemplate.update("UPDATE clientes SET nombre = ?, apellidos = ?, cif = ?, fecha_nacimiento = ? WHERE id = ?",
				new Object[] { cliente.getNombre(), cliente.getApellidos(), cliente.getCif(),
						cliente.getFechaNacimiento(), cliente.getId() });
		return cliente;
	}

	@Override
	public void borrar(Long id) {
		jdbcTemplate.update("DELETE FROM clientes WHERE id = ?", new Object[] { id });
	}

	@Override
	public int numeroRegistros() {
		String sql = "SELECT count(id) FROM clientes";

		int total = jdbcTemplate.queryForObject(sql, Integer.class);
		return total;
	}

}
