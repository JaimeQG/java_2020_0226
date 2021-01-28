package com.ipartek.formacion.spring.medicamentos;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ipartek.formacion.spring.medicamentos.entidades.Medicamento;
import com.ipartek.formacion.spring.medicamentos.repositorios.Dao;

@SpringBootApplication
public class SpringMedicamentosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringMedicamentosApplication.class, args);
	}

	static final private String NOMBRE_POJO = "Medicamento";

	static final private int MIN_LENGTH = 3;
	static final private int MAX_LENGTH_NOMBRE = 50;

	static final private int MAX_LENGTH_REFERENCIA = 10;

	static final protected String OP_CREAR = "1";
	static final protected String OP_ELIMINAR = "2";
	static final protected String OP_LISTAR = "3";
	static final protected String OP_SALIR = "s";

	private static String opcion = "";

	private static Scanner sc = null;
	// private static Scanner sc = new Scanner(System.in);

	@Autowired
	private Dao<Medicamento> dao;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("******************************");
		System.out.println("****  APP   MEDICAMENTO  *****");

		do {
			pintarMenu(NOMBRE_POJO);
			System.out.println("Selecciona una opcion:");
			sc = new Scanner(System.in);
			opcion = sc.nextLine();

			switch (opcion) {

			case OP_CREAR:
				crear();
				break;

			case OP_ELIMINAR:
				eliminar();
				break;

			case OP_LISTAR:
				listar();
				break;

			case OP_SALIR:
				System.out.println("Adios, vuelve pronto.....");
				break;

			default:
				System.out.println("*** Opcion no permitida");
				break;
			}

		} while (!OP_SALIR.equals(opcion));

		sc.close();
	}

	/**
	 * Obtiene todos los medicamentos ordenados Alfabeticamente
	 * 
	 * @return Collection<Medicamento>, si no existen libros Lista vacia pero no
	 *         nula
	 */

	private void listar() {

		System.out.println("-------------------------------------");
		System.out.println("       LISTADO DE MEDICAMENTOS       ");
		System.out.println("-------------------------------------");

		for (Medicamento medicamento : dao.obtenerTodos()) {
			System.out.println(String.format("[%s] (%s) %-17s  ........... [%.2f€]", medicamento.getId(),
					medicamento.getReferencia(), medicamento.getNombre(), medicamento.getPrecio()));
		}

		System.out.println("----------------------------------------");
		System.out.println("Número TOTAL de medicamentos en la BBDD: " + dao.numeroRegistros("id"));
		System.out.println("----------------------------------------");
		System.out.println("\n");

	}

	/**
	 * Eliminar un Medicamento por su identificador
	 * 
	 * @param id Long identificador
	 * @return
	 */
	private void eliminar() {

		// Mostramos un listado con los Medicamentos
		listar();

		// variables
		boolean flag = true;
		boolean isError = true;
		Long id = 0L;
		Medicamento medicamentoEliminar = null;

		do {
			do {
				try {
					System.out.println("Introduce el ID del medicamento que quieres borrar:");
					id = Long.parseLong(sc.nextLine());

					// si la linea de arriba lanza excepcion, estas de abajo nunca se ejecutaran
					isError = false;
				} catch (Exception e) {
					System.out.println("**error, no es un numero valido. Escribe un numero");
				}
			} while (isError);

			try {
				medicamentoEliminar = dao.obtenerPorId(id); // recuperamos el Medicamento a suprimir
				// si la linea de arriba lanza excepcion, estas de abajo nunca se ejecutaran
				flag = false;
			} catch (Exception e) {
				System.out.println("*** Lo sentimos pero no existe ese medicamento");
			}

		} while (flag);

		// Pedimos confirmacion sobre el borrado del medicamento
		// pedir confirmacion de referencia para eliminar
		flag = true;

		do {
			System.out.printf("Por favor escribe [%s] para eliminar o \"s\" para [S]alir\n",
					medicamentoEliminar.getReferencia());
			String referencia = sc.nextLine();

			if (OP_SALIR.equalsIgnoreCase(referencia)) {
				break; // salimos del bucle

			} else { // comprobar nombre del medicamento

				if (medicamentoEliminar.getReferencia().equalsIgnoreCase(referencia)) {
					try {
						dao.borrar(id);
						flag = false;
						System.out.println("-------------------------------------");
						System.out.println("Hemos dado de baja el medicamento:");
						System.out.println(String.format("[%s] (%s) %-17s  ........... [%.2f€]",
								medicamentoEliminar.getId(), medicamentoEliminar.getReferencia(),
								medicamentoEliminar.getNombre(), medicamentoEliminar.getPrecio()));
						System.out.println("-------------------------------------");

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("*** error: No coincide la referencia del medicamento **");
				}
			}
		} while (flag);

	}

	private void crear() {

		// variables
		String referencia = "";
		String nombre = "";
		BigDecimal precio = new BigDecimal(0);

		boolean isError = true;

		// pedimos datos del medicamento por consola
		do {
			try {
				referencia = validarString("referencia", MAX_LENGTH_REFERENCIA); // Referencia
			} catch (Exception e) {
				e.getMessage();
			}

			// Comprobamos si hay un Medicamento con la misma Referencia en la BBDD
			boolean existe = false;

			try {
				existe = dao.valorExiste("referencia", referencia.toUpperCase());

				if (existe) {
					System.out.println(
							"*** error: Ya hay en la BBBD un medicamento con la referencia (" + referencia + ")");
				} else {
					isError = false;
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		} while (isError);

		try {
			nombre = validarString("nombre", MAX_LENGTH_NOMBRE); // Nombre
		} catch (Exception e) {
			e.getMessage();
		}

		isError = true;

		do {
			try {
				System.out.println("Introduzca precio del medicamento:  \n"); // Precio medicamento
				String precioString = sc.nextLine();

				if (precioString.matches("\\d+\\.\\d\\d")) {
					precio = new BigDecimal(precioString);
					if (precio == null || precio.compareTo(new BigDecimal(0)) <= 0) {
						System.out.println("** error: el precio tiene que ser mayor que 0");
					} else {
						isError = false;
					}
				} else {
					System.out.println("**error: no es un número valido. Debe tener 2 decimales");
				}
			} catch (NumberFormatException e) {
				System.out.println("**error: no es un número valido");

			} catch (Exception e) {
				System.out.println("**error: no es un número valido");
			}
		} while (isError);

		// Crear un medicamento y setear valores

		// llamar al modelo para guardar en la BBDD

		try {
			Medicamento medicamentoNuevo = dao.agregar(new Medicamento(null, referencia, nombre, precio));

			// dao.crear(medicamentoNuevo);
			System.out.println("-------------------------------------");
			System.out.println("Medicamento guardado correctamente");
			System.out.println(medicamentoNuevo);
			System.out.println("-------------------------------------");
			isError = false;
		} catch (Exception e) {
			System.out.println("**error: No se ha podido crear el medicamento");
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
		System.out.println(" " + OP_CREAR + ".- Crear un " + nombrePojo);
		System.out.println(" " + OP_ELIMINAR + ".- Dar de baja un " + nombrePojo);
		System.out.println(" " + OP_LISTAR + ".- Listar todos los " + nombrePojo + "s");
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
	private static String validarString(String propiedad, int longitudMaxima) throws Exception {

		boolean isError = true;
		String nombre = "";

		// Repetir hasta que no haya error
		do {
			try {
				System.out.println("Introduzca el " + propiedad + " del " + NOMBRE_POJO + " : \n");
				nombre = sc.nextLine();

				// si la linea de arriba lanza excepcion, estas de abajo nunca se ejecutaran
				if (nombre.trim().length() < MIN_LENGTH || nombre.trim().length() > longitudMaxima
						|| !nombre.matches("\\p{Lu}[ \\p{L}\\d+]*")) {
					System.out.println("**error, el " + propiedad + " debe tener más de " + MIN_LENGTH
							+ " letras y menos de " + longitudMaxima + ". '" + nombre + "' tiene " + nombre.length()
							+ " caracter(es) \n");
				} else {
					isError = false;
				}
			} catch (Exception e) {
				throw new Exception("*** error: No es un valor correcto");
			}
		} while (isError);

		return nombre;
	}

}
