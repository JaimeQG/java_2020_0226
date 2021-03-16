package com.ipartek.formacion.ejemplofinal.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el Cliente del supermercado
 * 
 * @author Jaime Quintana
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

	/**
	 * Necesario para los elementos Serializables
	 */
	private static final long serialVersionUID = 3086908301375558731L;

	private Long id;
	private String nombre;
	private String apellidos;
	private String cif;
	private LocalDate fechaNacimiento;

	private Set<Factura> facturas;
}