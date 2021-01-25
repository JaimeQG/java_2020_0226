package com.ipartek.formacion.euromillones.controladores;

import com.ipartek.formacion.euromillones.accesodatos.Dao;
import com.ipartek.formacion.euromillones.accesodatos.SorteoDaoMySql;
import com.ipartek.formacion.euromillones.modelos.Sorteo;

public class Configuracion {

	public static Dao<Sorteo> daoEuromillones = SorteoDaoMySql.getInstancia();

}
