package com.ipartek.formacion.uf1466.bibliotecas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Miscelania {

	/**
	 * 
	 * @return String nombre del fichero: yyyy-MM-dd-HH-mm-ss.csv
	 */
	public static String nombreFicheroCSV() {

		Date fechaActual = new Date();

		// Formateando la fecha:
		DateFormat formatoHora = new SimpleDateFormat("HH-mm-ss");
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

		return (formatoFecha.format(fechaActual) + "-" + formatoHora.format(fechaActual) + ".csv");
	}

}
