package com.ipartek.formacion.EjecutarComandosMSDOS;

import java.io.FileInputStream;
import java.io.OutputStream;

public class RestoreDB {

	public static void main(String[] args) {

		try {
			Process p = Runtime.getRuntime().exec("C:/tools/mysql/current/bin/mysql -u usuario -padmin");

			OutputStream os = p.getOutputStream();
			FileInputStream fis = new FileInputStream("C:\\Users\\usuario\\libreria_dump.sql");
			byte[] buffer = new byte[1000];

			int leido = fis.read(buffer);
			while (leido > 0) {
				os.write(buffer, 0, leido);
				leido = fis.read(buffer);
			}

			os.flush();
			os.close();
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
