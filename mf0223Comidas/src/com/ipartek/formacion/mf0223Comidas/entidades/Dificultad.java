package com.ipartek.formacion.mf0223Comidas.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la dificultad de elaboración del plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dificultad implements Serializable {

	private static final long serialVersionUID = -9072758217051515820L;

	private Long id;
	private String nombre;
}
