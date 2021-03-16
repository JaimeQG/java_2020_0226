package com.ipartek.formacion.ejemplofinal.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.ejemplofinal.entidades.Carrito;

/**
 * Representa la sesión de usuario/HttpSession
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@WebListener
public class SesionListener implements HttpSessionListener {

	/**
	 * Creación de la sesión de usuario/Httpsession
	 * 
	 * @param se Evento de HttpSession
	 * 
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Carrito carrito = new Carrito();
		se.getSession().setAttribute("carrito", carrito);
	}

	/**
	 * Destrucción de la sesión de usuario/Httpsession
	 * 
	 * @param se Evento de HttpSession
	 * 
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// No es necesario
	}

}
