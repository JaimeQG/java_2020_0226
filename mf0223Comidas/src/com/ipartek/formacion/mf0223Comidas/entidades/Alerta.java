package com.ipartek.formacion.mf0223Comidas.entidades;

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
@AllArgsConstructor
@NoArgsConstructor
public class Alerta implements Serializable {

	private static final long serialVersionUID = -8728849028407423979L;

	private String nivel;
	private String texto;
}
