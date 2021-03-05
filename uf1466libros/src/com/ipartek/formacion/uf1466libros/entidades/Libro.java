package com.ipartek.formacion.uf1466libros.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {

	private Long id;
	private String titulo;
	private String ISBN;

	private Autor autor;

}
