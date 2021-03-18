package com.ipartek.formacion.mf0223Comidas.accesodatos;

import java.io.FileInputStream;
import java.io.OutputStream;

import lombok.extern.java.Log;

@Log
public class DatabaseMySql implements Database {

	static final String MYSQL_RESTORE = "mysql -u " + Config.usuario + " -p" + Config.password + " ";
	private Process runProcess = null;

	@Override
	public void restoreDatabase(String fileRestore) {
		int processComplete;
		// Restore BBDD ORIGEN
		log.info(MYSQL_RESTORE + fileRestore);

		try {
			runProcess = Runtime.getRuntime().exec(MYSQL_RESTORE);
		} catch (Exception e) {
			throw new AccesoDatosException("Error al ejecutar el restore de la BB.DD.", e);
		}

		try (OutputStream os = runProcess.getOutputStream(); FileInputStream fis = new FileInputStream(fileRestore)) {
			byte[] buffer = new byte[1000];

			int leido;
			while ((leido = fis.read(buffer)) > 0) {
				os.write(buffer, 0, leido);
			}

			os.flush();
		} catch (Exception e) {
			throw new AccesoDatosException("Error al ejecutar el restore de la BB.DD.", e);
		}

		try {
			processComplete = runProcess.waitFor();
			if (processComplete == 0) {
				log.info("Restore de la BBDD. Todo correcto");
			} else {
				log.info("Restore de la BBDD. Ha habido algún error");
			}
		} catch (Exception e) {
			Thread.currentThread().interrupt();
			throw new AccesoDatosException("Error al ejecutar el restore de la BB.DD.", e);
		}
	}
}
