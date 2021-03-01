package com.ipartek.formacion.uf1466.accesodatos;

import com.ipartek.formacion.uf1466.entidades.Cliente;

public interface ClienteDao extends Dao<Cliente> {
	default Iterable<String> nombreColumnas() {
		throw new AccesoDatosException("ESTE MÉTODO NO ESTÁ IMPLEMENTADO");
	}

}
