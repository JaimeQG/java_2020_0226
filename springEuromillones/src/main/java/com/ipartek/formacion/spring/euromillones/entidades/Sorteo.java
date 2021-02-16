package com.ipartek.formacion.spring.euromillones.entidades;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sorteo {

	private Long id;
	private String numero_1, numero_2, numero_3, numero_4, numero_5;
	private String estrella_1, estrella_2;
	private LocalDate fechaSorteo;

}