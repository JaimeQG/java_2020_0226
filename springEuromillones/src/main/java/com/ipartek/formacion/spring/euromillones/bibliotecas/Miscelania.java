package com.ipartek.formacion.spring.euromillones.bibliotecas;

import java.util.Arrays;
import java.util.Random;

import javax.swing.text.MaskFormatter;

public class Miscelania {

	public static String derechaString(String value, int length) {
		return value.substring(value.length() - length);
	}

	public static void pl(Object mensaje) {
		System.out.println(mensaje);
	}

	public static void mostrarErrores(Throwable e) {
		pl(e.getMessage());

		while (e.getCause() != null) {
			pl(e.getCause().getLocalizedMessage());
			e = e.getCause();
		}
	}

	/**
	 * 
	 * @param array
	 * @param numero
	 * @param rellenos
	 * @return true si lo contiene/ false si no
	 */

	public static boolean contiene(int[] array, int numero, int rellenos) {
		boolean resultado = false;

		for (int i = 0; i < rellenos; i++) {
			if (numero == array[i]) {
				resultado = true;
			}
		}

		return resultado;
	}

	/**
	 * 
	 * @param numeros
	 * @param numeroMaximo
	 * @return array con los números del sorteo ordendos de menor a mayor
	 */

	public static int[] sortear(int numeros, int numeroMaximo) {
		int combinacion[] = new int[numeros];
		Random random = new Random();

		for (int i = 0; i < numeros; i++) {
			do {
				combinacion[i] = random.nextInt(numeroMaximo) + 1;
			} while (contiene(combinacion, combinacion[i], i));
		}

		Arrays.sort(combinacion);

		return combinacion;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */

	public static MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}

}
