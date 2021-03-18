package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.accesodatos.DaoFabrica;
import com.ipartek.formacion.mf0223Comidas.accesodatos.Database;

public class RestaurarDatabaseImpl implements RestaurarDatabase {

	private Database dataBase = DaoFabrica.getDatabase();

	public void restaurarBaseDeDatos(String fileRestore) {
		dataBase.restoreDatabase(fileRestore);
	}
}
