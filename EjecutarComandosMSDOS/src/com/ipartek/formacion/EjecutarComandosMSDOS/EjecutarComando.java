package com.ipartek.formacion.EjecutarComandosMSDOS;

import java.io.IOException;

public class EjecutarComando {

	public static void main(String[] args) throws IOException, InterruptedException {
		Process runProcess = Runtime.getRuntime().exec("notepad.exe C:\\tools\\mysql\\supermercado_backup.sql");
		int processComplete = runProcess.waitFor();

		if (processComplete == 0) {
			System.out.println("Todo correcto");
		} else {
			System.out.println("Ha habido algún error");
		}
	}

}
