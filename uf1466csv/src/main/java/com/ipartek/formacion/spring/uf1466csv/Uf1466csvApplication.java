package com.ipartek.formacion.spring.uf1466csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ipartek.formacion.spring.uf1466csv.accesodatos.MedicamentoDao;
import com.ipartek.formacion.spring.uf1466csv.accesodatos.MedicamentoDaoJdbc;
import com.ipartek.formacion.spring.uf1466csv.entidades.Medicamento;

//@SpringBootApplication
public class Uf1466csvApplication {

	private static final MedicamentoDao DAO = MedicamentoDaoJdbc.getInstancia();

	public static void main(String[] args) {
		// SpringApplication.run(Uf1466csvApplication.class, args);

		System.out.println("Nombre del fichero: " + nombreFicheroCSV());

		FileWriter fw = null;
		try {
			fw = new FileWriter(nombreFicheroCSV());
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Iterable<String> tablas = DAO.nombreTablas();
		for (String tabla : tablas) {
			System.out.println(tabla);
		}

		StringBuilder bld = new StringBuilder();
		Iterable<String> columnas = DAO.nombreColumnas();
		for (String columna : columnas) {
			bld.append(columna.toUpperCase() + ";");
		}
		String cabecera = bld.toString();

		System.out.println(cabecera);
		pw.println(cabecera);

		Iterable<Medicamento> medicamentos = DAO.obtenerTodos();

		for (Medicamento medicamento : medicamentos) {
			System.out.println(medicamento);
			pw.println(medicamento.getId() + ";" + medicamento.getReferencia() + ";" + medicamento.getNombre() + ";"
					+ medicamento.getPrecio() + ";");
		}

		pw.close();
	}

	/**
	 * 
	 * @return String nombre del fichero: yyyy-MM-dd-HH-mm-ss.csv
	 */
	private static String nombreFicheroCSV() {

		Date fechaActual = new Date();

		// Formateando la fecha:
		DateFormat formatoHora = new SimpleDateFormat("HH-mm-ss");
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

		return (formatoFecha.format(fechaActual) + "-" + formatoHora.format(fechaActual) + ".csv");
	}

}
