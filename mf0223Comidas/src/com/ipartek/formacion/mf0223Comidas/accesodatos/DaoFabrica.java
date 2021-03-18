package com.ipartek.formacion.mf0223Comidas.accesodatos;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;
import com.ipartek.formacion.mf0223Comidas.entidades.Origen;
import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

public class DaoFabrica {

	private DaoFabrica() {
	}

	private static final Dao<Plato> daoPlato = new PlatoDaoMySql();
	private static final Dao<Categoria> daoCategoria = new CategoriaDaoMySql();
	private static final Dao<Origen> daoOrigen = new OrigenDaoMySql();

	private static final Database dataBase = new DatabaseMySql();

	public static Dao<Plato> getDaoPlato() {
		return daoPlato;
	}

	public static Dao<Categoria> getDaoCategoria() {
		return daoCategoria;
	}

	public static Dao<Origen> getDaoOrigen() {
		return daoOrigen;
	}

	public static Database getDatabase() {
		return dataBase;
	}
}
