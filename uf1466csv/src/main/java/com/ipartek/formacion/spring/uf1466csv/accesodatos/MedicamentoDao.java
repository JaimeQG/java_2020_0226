package com.ipartek.formacion.spring.uf1466csv.accesodatos;

import com.ipartek.formacion.spring.uf1466csv.entidades.Medicamento;

public interface MedicamentoDao extends Dao<Medicamento> {
	default Iterable<String> nombreColumnas() {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

	default Iterable<String> nombreTablas() {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}
}
