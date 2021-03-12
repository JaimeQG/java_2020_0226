package com.ipartek.formacion.EjecutarComandosMSDOS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;

public class BackupBD {

	private static Process runProcess = null;
	// private static int processComplete;
	// private static int leido;

	public static void main(String[] args) throws IOException, InterruptedException {

		// BBDD existentes
		runProcess = Runtime.getRuntime().exec("mysql -u usuario -padmin -e \"show databases\"");

		try (InputStream is = runProcess.getInputStream();
				FileOutputStream fos = new FileOutputStream("bases_datos.txt")) {
			byte[] buffer = new byte[1000];

			int leido;
			while ((leido = is.read(buffer)) > 0) {
				fos.write(buffer, 0, leido);
			}
		}

		int processComplete = runProcess.waitFor();

		if (processComplete == 0) {
			System.out.println("BBDD. Todo correcto");
		} else {
			System.out.println("BBDD. Ha habido algún error");
		}

		TreeMap<Integer, String> listaDB = new TreeMap<Integer, String>();
		String file = "bases_datos.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int indice = 0;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				listaDB.put(indice, line);
				indice = indice + 1;
			}
		} catch (IOException e) {
			System.out.println("Reading the file " + file + ". An error occurred !!!");
			e.printStackTrace();
		}

		// System.out.println(listaDB.values());
		listaDB.pollFirstEntry();
		// System.out.println(listaDB.values());
		System.out.println(listaDB);

		// Backup de la BBDD existente
		runProcess = Runtime.getRuntime().exec("mysqldump -u usuario -padmin supermercado");

		try (InputStream is = runProcess.getInputStream();
				FileOutputStream fos = new FileOutputStream("backup_pruebas_super.sql")) {
			byte[] buffer = new byte[1000];

			int leido;
			while ((leido = is.read(buffer)) > 0) {
				fos.write(buffer, 0, leido);
			}
		}

		processComplete = runProcess.waitFor();

		if (processComplete == 0) {
			System.out.println("Backup de la BBDD. Todo correcto");
		} else {
			System.out.println("Backup de la BBDD. Ha habido algún error");
		}

		// CREATE DATABASE nueva BBDD
		runProcess = Runtime.getRuntime().exec("mysql -u usuario -padmin -e \"CREATE DATABASE NUEVABBDD\"");

		processComplete = runProcess.waitFor();

		if (processComplete == 0) {
			System.out.println("Creada nueva BBDD. Todo correcto");
		} else {
			System.out.println("NO creada nueva BBDD. Ha habido algún error");
		}

		// Restore BBDD existente en la nueva BBDD
		runProcess = Runtime.getRuntime().exec("mysql -u usuario -padmin NUEVABBDD");

		try (OutputStream os = runProcess.getOutputStream();
				FileInputStream fis = new FileInputStream("backup_pruebas_super.sql")) {
			byte[] buffer = new byte[1000];

			int leido;
			while ((leido = fis.read(buffer)) > 0) {
				os.write(buffer, 0, leido);
			}
		}

		processComplete = runProcess.waitFor();

		if (processComplete == 0) {
			System.out.println("Restore de la BBDD. Todo correcto");
		} else {
			System.out.println("Restore de la BBDD. Ha habido algún error");
		}
	}
}
