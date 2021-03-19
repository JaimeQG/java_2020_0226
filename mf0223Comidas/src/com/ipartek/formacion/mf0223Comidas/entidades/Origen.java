package com.ipartek.formacion.mf0223Comidas.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el origen del plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Origen implements Serializable {

	private static final long serialVersionUID = 5785176740710216923L;

	private Long id;
	private String nombre;
}
