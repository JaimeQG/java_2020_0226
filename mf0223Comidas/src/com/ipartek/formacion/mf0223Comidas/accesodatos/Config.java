package com.ipartek.formacion.mf0223Comidas.accesodatos;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Config {

	static final String usuario = "usuario";
	static final String password = "admin";

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
			throw new AccesoDatosException("No se ha encontrado el JNDI de comidas", e);
		}
	}
}
