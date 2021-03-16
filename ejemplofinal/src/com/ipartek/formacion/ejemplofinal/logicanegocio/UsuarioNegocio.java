package com.ipartek.formacion.ejemplofinal.logicanegocio;

import com.ipartek.formacion.ejemplofinal.entidades.Usuario;

/**
 * Representa la lógica del negocio para la clase Usuario
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public interface UsuarioNegocio {

	/**
	 * Valida el si el usuario que entra en la aplicación es además cliente
	 * 
	 * @param usuario usuario que ha hecho login en el supermercado
	 * @return true, si es cliente. False, si no lo es
	 */
	boolean validarUsuario(Usuario usuario);
}
