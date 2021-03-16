package com.ipartek.formacion.ejemplofinal.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el Usuario del supermercado (que puede ser o no también Cliente).
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
	/**
	 * Necesario para los elementos Serializables
	 */
	private static final long serialVersionUID = 6810679589257920056L;

	private Long id;
	private String email;
	private String password;
	private Cliente cliente;
}
