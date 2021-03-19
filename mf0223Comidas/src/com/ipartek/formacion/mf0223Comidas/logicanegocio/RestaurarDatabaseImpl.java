package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.accesodatos.Database;

/**
 * Representa la implentación de la clase RestaurarDatabase
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public class RestaurarDatabaseImpl implements RestaurarDatabase {

	private Database dataBase = DaoFabrica.getDatabase();

	/**
	 * Restaura una BB.DD de MySql
	 * 
	 * @param fileRestore fichero con las sentencias MySql para hacer el restore de
	 *                    la BB.DD.
	 * 
	 */
	public void restaurarBaseDeDatos(String fileRestore) {
		dataBase.restoreDatabase(fileRestore);
	}
}
