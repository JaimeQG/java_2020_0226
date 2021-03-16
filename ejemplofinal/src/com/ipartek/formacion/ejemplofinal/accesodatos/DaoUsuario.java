package com.ipartek.formacion.ejemplofinal.accesodatos;

import com.ipartek.formacion.ejemplofinal.entidades.Usuario;

/**
 * DAO para definir las operaciones de que no son CRUD para la entidad
 * Usuario<br>
 * Interferimos la clase generica {@code Dao<Usuario>}
 * 
 * @author Jaime Quintana
 *
 */
public interface DaoUsuario extends Dao<Usuario> {
	Usuario obtenerPorEmail(String email);
}