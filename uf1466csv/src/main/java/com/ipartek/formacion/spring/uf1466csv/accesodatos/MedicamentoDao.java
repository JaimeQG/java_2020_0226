package com.ipartek.formacion.spring.uf1466csv.accesodatos;

import com.ipartek.formacion.spring.uf1466csv.entidades.Medicamento;

public interface MedicamentoDao extends Dao<Medicamento> {
	default Iterable<String> nombreColumnas() {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

	default void borrar(String referencia) {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}
}
