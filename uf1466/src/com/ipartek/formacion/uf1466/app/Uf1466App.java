package com.ipartek.formacion.uf1466.app;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.ipartek.formacion.uf1466.accesodatos.ClienteDao;
import com.ipartek.formacion.uf1466.accesodatos.ClienteDaoJdbc;
import com.ipartek.formacion.uf1466.bibliotecas.Miscelania;
import com.ipartek.formacion.uf1466.entidades.Cliente;

public class Uf1466App {

	private static ClienteDao DAO = ClienteDaoJdbc.getInstancia();
	private static String SPLIT = ";";

	public static void main(String[] args) {

		System.out.println("Nombre del fichero: " + Miscelania.nombreFicheroCSV());

		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(Miscelania.nombreFicheroCSV());
			pw = new PrintWriter(fw);

			StringBuilder bld = new StringBuilder();
			Iterable<String> columnas = DAO.nombreColumnas();
			for (String columna : columnas) {
				bld.append(columna.toUpperCase() + SPLIT);
			}
			String cabecera = bld.toString();

			System.out.println(cabecera);
			pw.println(cabecera);

			Iterable<Cliente> clientes = DAO.obtenerTodos();

			for (Cliente cliente : clientes) {

				int isActivo = cliente.isActivo() ? 1 : 0;

				System.out.println(cliente);
				pw.println(cliente.getCodigo() + SPLIT + cliente.getNombre() + SPLIT + cliente.getEmail() + SPLIT
						+ cliente.getTelefono() + SPLIT + cliente.getDireccion() + SPLIT + cliente.getPoblacion()
						+ SPLIT + cliente.getCodigopostal() + SPLIT + cliente.getIdentificador() + SPLIT + isActivo
						+ SPLIT);
			}

			pw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
