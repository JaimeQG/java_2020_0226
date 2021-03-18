package com.ipartek.formacion.mf0223Comidas.accesodatos;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Clase para la conexión con la BBDD MySQL
 * 
 * @author Jaime Quintana
 * @version 1.0
 * 
 */
public class Config {

	static final String USUARIO = "usuario";
	static final String PASSWORD = "admin";

	private static final String JDBC_COMIDAS = "jdbc/comidas";

	private Config() {
	}

	static final DataSource dataSource;

	static {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup(JDBC_COMIDAS);
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el JNDI de " + JDBC_COMIDAS, e);
		}
	}
}
