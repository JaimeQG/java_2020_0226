
package com.ipartek.formacion.uf1465loginusuario.accesodatos;

import com.ipartek.formacion.uf1465loginusuario.entidades.Usuario;

public interface UsuarioDao extends Dao<Usuario> {
	default Usuario obtenerPorEmail(String email) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

}