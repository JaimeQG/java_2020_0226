package com.ipartek.formacion.uf1466.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

	private int codigo;
	private String nombre;
	private String email;
	private int telefono;
	private String direccion;
	private String poblacion;
	private int codigopostal;
	private String identificador;
	private boolean activo;

}
