package com.ipartek.formacion.mf0223Comidas.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la categoría del plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable {

	private static final long serialVersionUID = -7045349264099962318L;

	private Long id;
	private String nombre;
}
