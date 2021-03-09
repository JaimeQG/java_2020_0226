package com.ipartek.formacion.EjecutarComandosMSDOS;

import java.util.Scanner;

public class Triangulo {

	private static Scanner sc = new Scanner(System.in);
	private static String opcion = "";

	public static void main(String[] args) {

		System.out.println("Base y altura del triángulo:");
		opcion = sc.nextLine();
		generateTriangle(opcion);

		System.out.println("Idioma del saludo:");
		opcion = sc.nextLine();
		sayHello(opcion);

		String[] cars = { "Mazda", "BMW", "Toyota", "Seat" };
		getMaxLength(cars);
	}

	private static void getMaxLength(String[] cars) {

		String stringMax = "";

		for (int i = 0; i < cars.length; i++) {
			if (cars[i].length() > stringMax.length()) {
				stringMax = cars[i];
			}
		}

		System.out.println("\n" + stringMax);

	}

	private static void generateTriangle(String opcion) {
		int size = Integer.parseInt(opcion);
		String salida = "";

		for (int i = 0; i <= size-1; i++) {
			salida = salida + "\n" + "#";
		}
		System.out.println(salida);
	}

	private static void sayHello(String lang) {

		switch (lang) {
		case "es":
			System.out.println("hola");
			break;
		case "en":
			System.out.println("hello");
			break;
		case "fr":
			System.out.println("bonjour");
			break;
		case "it":
			System.out.println("ciao");
			break;

		default:
			System.out.println("_Language not supported_");
			break;
		}
	}

}
