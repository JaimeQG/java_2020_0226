package com.ipartek.formacion.ejemplofinal.logicanegocio;

import com.ipartek.formacion.ejemplofinal.accesodatos.DaoFabrica;
import com.ipartek.formacion.ejemplofinal.accesodatos.DaoUsuario;
import com.ipartek.formacion.ejemplofinal.entidades.Usuario;

/**
 * Representa la implentación de la clase UsuarioNegocio
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public class UsuarioNegocioImpl implements UsuarioNegocio {

	private DaoUsuario dao = DaoFabrica.getDaoUsuario();

	/**
	 * Valida el si el usuario que entra en la aplicación es además cliente
	 * 
	 * @param usuario usuario que ha hecho login en el supermercado
	 * @return true, si es cliente. false, si no lo es
	 */
	@Override
	public boolean validarUsuario(Usuario usuario) {
		Usuario usuarioBdd = dao.obtenerPorEmail(usuario.getEmail());

		if (usuarioBdd != null && usuario.getPassword().equals(usuarioBdd.getPassword())) {
			usuario.setId(usuarioBdd.getId());
			usuario.setCliente(usuarioBdd.getCliente());

			return true;
		} else {
			return false;
		}
	}

}
