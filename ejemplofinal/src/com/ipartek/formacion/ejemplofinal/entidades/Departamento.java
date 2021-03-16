package com.ipartek.formacion.ejemplofinal.entidades;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el departamento al que pertenece un porducto del supermercado
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamento implements Serializable {

	/**
	 * Necesario para los elementos Serializables
	 */
	private static final long serialVersionUID = 7623251760150200816L;

	private Long id;
	private String nombre;
	private String descripcion;

	private Set<Producto> productos;
}
