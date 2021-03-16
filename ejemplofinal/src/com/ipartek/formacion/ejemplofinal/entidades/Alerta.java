package com.ipartek.formacion.ejemplofinal.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Alerta para sacar en pantalla alert de Bootstrap (nivel y mensaje):
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta implements Serializable {

	private static final long serialVersionUID = 4718434984524239877L;

	private String nivel;
	private String mensaje;
}
