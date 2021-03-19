package com.ipartek.formacion.mf0223Comidas.accesodatos;

import com.ipartek.formacion.mf0223Comidas.entidades.Categoria;
import com.ipartek.formacion.mf0223Comidas.entidades.Dificultad;
import com.ipartek.formacion.mf0223Comidas.entidades.Origen;
import com.ipartek.formacion.mf0223Comidas.entidades.Plato;

/**
 * DAO Fabrica para las entidades: Plato, Categoria, Origen y Dificaultad.
 * Tambien para la Base de Datos.<br>
 * Separa la capa de acceso a los datos (MySql) de su implementación
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public class DaoFabrica {

	private DaoFabrica() {
	}

	private static final Dao<Plato> daoPlato = new PlatoDaoMySql();
	private static final Dao<Categoria> daoCategoria = new CategoriaDaoMySql();
	private static final Dao<Origen> daoOrigen = new OrigenDaoMySql();
	private static final Dao<Dificultad> daoDificultad = new DificultadDaoMySql();

	private static final Database dataBase = new DatabaseMySql();

	/**
	 * Obtener objeto de la clase PlatoDaoMySql
	 * 
	 * @return objeto Plato para el acceso a la BB.DD de MySql
	 */
	public static Dao<Plato> getDaoPlato() {
		return daoPlato;
	}

	/**
	 * Obtener objeto de la clase CategoriaDaoMySql
	 * 
	 * @return objeto Categoria para el acceso a la BB.DD de MySql
	 */
	public static Dao<Categoria> getDaoCategoria() {
		return daoCategoria;
	}

	/**
	 * Obtener objeto de la clase OrigenDaoMySql
	 * 
	 * @return objeto Origen para el acceso a la BB.DD de MySql
	 */
	public static Dao<Origen> getDaoOrigen() {
		return daoOrigen;
	}

	/**
	 * Obtener objeto de la clase DificultadDaoMySql
	 * 
	 * @return objeto Dificultad para el acceso a la BB.DD de MySql
	 */
	public static Dao<Dificultad> getDaoDificultad() {
		return daoDificultad;
	}

	/**
	 * Obtener objeto de la clase DatabaseMySql
	 * 
	 * @return objeto Database MySql
	 */
	public static Database getDatabase() {
		return dataBase;
	}
}
