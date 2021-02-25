package com.ipartek.fomacion.uf1465loginusuario.controladores;

import com.ipartek.formacion.uf1465loginusuario.accesodatos.UsuarioDao;
import com.ipartek.formacion.uf1465loginusuario.accesodatos.UsuarioDaoMySql;

public class Configuracion {
	public static UsuarioDao daoUsuario = UsuarioDaoMySql.getInstancia();

}
