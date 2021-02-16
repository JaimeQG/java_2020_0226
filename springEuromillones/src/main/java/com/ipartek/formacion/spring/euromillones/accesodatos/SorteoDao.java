package com.ipartek.formacion.spring.euromillones.accesodatos;

import com.ipartek.formacion.spring.euromillones.entidades.Sorteo;

public interface SorteoDao extends Dao<Sorteo> {

	default int numeroRegistros() {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

}
