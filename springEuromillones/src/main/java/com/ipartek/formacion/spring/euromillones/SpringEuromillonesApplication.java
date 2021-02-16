package com.ipartek.formacion.spring.euromillones;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ipartek.formacion.spring.euromillones.accesodatos.SorteoDao;
import com.ipartek.formacion.spring.euromillones.accesodatos.SorteoDaoJdbc;
import com.ipartek.formacion.spring.euromillones.entidades.Sorteo;

@SpringBootApplication
public class SpringEuromillonesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringEuromillonesApplication.class, args);
	}

	static final private String NOMBRE_POJO = "Sorteo";

	static final private int NUMEROS = 5;
	static final private int ESTRELLAS = 2;

	static final private int NUMERO_MIN = 1;
	static final private int NUMERO_MAX = 50;

	static final private int NUMERO_MAX_ESTRELLA = 12;

	static final protected String OP_LISTAR = "1";
	static final protected String OP_CREAR = "2";
	static final protected String OP_SALIR = "s";

	private static final SorteoDao DAO = SorteoDaoJdbc.getInstancia();

	private static String opcion = "";

	private static Scanner sc = new Scanner(System.in);

	@Override
	public void run(String... args) throws Exception {

		System.out.println("****************************");
		System.out.println("***  APP   EUROMILLONES  ***");

		do {
			pintarMenu(NOMBRE_POJO);
			System.out.println("Selecciona una opcion:");
			opcion = sc.nextLine();

			switch (opcion) {
			case OP_LISTAR:
				listar();
				break;

			case OP_CREAR:
				crear();
				break;

			case OP_SALIR:
				System.out.println("Adios, vuelve pronto.....");
				break;

			default:
				System.out.println("* Opcion no permitida");
				break;
			}

		} while (!OP_SALIR.equalsIgnoreCase(opcion));

	}

	/**
	 * Obtiene todos los libros ordenados Alfabeticamente
	 * 
	 * @return Collection<Libro>, si no existen libros Lista vacia pero no nula
	 */

	private static void listar() {

		System.out.println("--------------------------------");
		System.out.println("       LISTADO DE SORTEOS       ");
		System.out.println("--------------------------------");

		for (Sorteo sorteo : DAO.obtenerTodos()) {
			System.out.println(String.format("[%s] (%s) %s - %s - %s - %s - %s *(%s - %s)* ", sorteo.getId(),
					sorteo.getFechaSorteo(), sorteo.getNumero_1(), sorteo.getNumero_2(), sorteo.getNumero_3(),
					sorteo.getNumero_4(), sorteo.getNumero_5(), sorteo.getEstrella_1(), sorteo.getEstrella_2()));
		}

		System.out.println("----------------------------------------");
		System.out.println("Número TOTAL de soteros de Euromillones en la BBDD: " + DAO.numeroRegistros());
		System.out.println("----------------------------------------");
		System.out.println("\n");

	}

	private static void crear() {

		// variables
		boolean isError = true;

		LocalDate fechalocalDate = null;
		;

		int[] numeros = new int[NUMEROS];
		int[] estrellas = new int[ESTRELLAS];

		String numeroString = null;
		int numero;

		// Fecha del sorteo
		do {
			System.out.println("Introduzca la fecha con formato [dd/mm/yyyy] \n");
			String fecha = sc.nextLine();

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date testDate = null;

			try {
				testDate = df.parse(fecha);
			} catch (Exception e) {
				System.out.println("invalid format");
			}

			if (!df.format(testDate).equals(fecha)) {
				System.out.println("Fecha incorrecta !!");
			} else {
				// Baeldung
				fechalocalDate = new java.sql.Date(testDate.getTime()).toLocalDate();
				isError = false;
			}
		} while (isError);

		for (int i = 0; i < NUMEROS; i++) {
			isError = true;
			do {
				try {
					System.out.println("Introduzca un número (" + (i + 1) + ") entre el " + NUMERO_MIN + " y el "
							+ NUMERO_MAX + ": \n"); // Precio
					// medicamento
					numeroString = sc.nextLine();
					numero = Integer.parseInt(numeroString);

					if (numero < 0 || numero > 50) {
						System.out.println(
								"** error: el numero tiene que estar entre el " + NUMERO_MIN + " y el " + NUMERO_MAX);
					} else {
						if (!repetido(numeros, numero, i)) {
							numeros[i] = numero;
							isError = false;
						} else {
							System.out.println("** error: el numero " + numero + " está repetido");
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("**error: no es un número valido");

				} catch (Exception e) {
					System.out.println("**error: no es un número valido");
				}
			} while (isError);
		}

		for (int i = 0; i < ESTRELLAS; i++) {
			isError = true;
			do {
				try {
					System.out.println("Introduzca una estrella (" + (i + 1) + ") entre el " + NUMERO_MIN + " y el "
							+ NUMERO_MAX_ESTRELLA + ": \n"); // Precio
					// medicamento
					numeroString = sc.nextLine();
					numero = Integer.parseInt(numeroString);

					if (numero < 0 || numero > 12) {
						System.out.println("** error: el numero tiene que estar entre el " + NUMERO_MIN + " y el "
								+ NUMERO_MAX_ESTRELLA);
					} else {
						if (!repetido(estrellas, numero, i)) {
							estrellas[i] = numero;
							isError = false;
						} else {
							System.out.println("** error: el numero " + numero + " está repetido");
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("**error: no es un número valido");

				} catch (Exception e) {
					System.out.println("**error: no es un número valido");
				}
			} while (isError);
		}

		// Ordenamos los arrays con los números
		Arrays.sort(numeros);
		Arrays.sort(estrellas);

		// Crear un sorteo y setear valores

		// llamar al modelo para guardar en la BBDD
		try {
			Sorteo sorteo = DAO.agregar(new Sorteo(null, String.valueOf(numeros[0]), String.valueOf(numeros[1]),
					String.valueOf(numeros[2]), String.valueOf(numeros[3]), String.valueOf(numeros[4]),
					String.valueOf(estrellas[0]), String.valueOf(estrellas[1]), fechalocalDate));

			System.out.println("-------------------------------------");
			System.out.println("Sorteo guardado correctamente");
			System.out.println(sorteo);
			System.out.println("-------------------------------------");
		} catch (Exception e) {
			System.out.println("**error: No se ha podido crear el sorteo");
		}
	}

	/**
	 * Se encraga de pintar las pociones del menu.
	 * 
	 * @param nombrePojo nombre del recurso que se gestiona en esta App
	 * 
	 */
	private static void pintarMenu(final String nombrePojo) {

		System.out.println("******************************");
		System.out.println(" " + OP_LISTAR + ".- Listar todos los " + nombrePojo + "s");
		System.out.println(" " + OP_CREAR + ".- Crear un " + nombrePojo);
		System.out.println(" ");
		System.out.println(" " + OP_SALIR + " - Salir");
		System.out.println("******************************");
	}

	/**
	 * Valida que los datos introducidos por pantalla son de tipo String
	 * 
	 * @param nombre de la propiedad de tipo String
	 * @return valor de tipo String cuya longitud sea > 2 y < 50 caracteres
	 */
	public static boolean repetido(int[] array, int numero, int rellenos) {
		boolean resultado = false;

		for (int i = 0; i < rellenos; i++) {
			if (numero == array[i]) {
				resultado = true;
			}
		}

		return resultado;
	}
}
