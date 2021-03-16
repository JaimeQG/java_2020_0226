package com.ipartek.formacion.mf0223Comidas.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alerta implements Serializable {

	private static final long serialVersionUID = -8728849028407423979L;

	private String nivel;
	private String texto;
}
