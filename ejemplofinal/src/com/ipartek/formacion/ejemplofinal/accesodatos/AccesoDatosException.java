package com.ipartek.formacion.ejemplofinal.accesodatos;

/**
 * Representa la captura de los errores en tiempo de ejecución para el acceso a
 * datos (implementaciones del {@code Dao<T>}, siendo T un objeto)
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public class AccesoDatosException extends RuntimeException {

	private static final long serialVersionUID = -1699889400014234381L;

	/**
	 * Constructor vacío de la clase
	 * 
	 */
	public AccesoDatosException() {
		super();
	}

	/**
	 * Constructor con todos los argumentos. Devuelve el mensaje de error de la
	 * excepción en la ejecución
	 * 
	 * @param message:            mensaje de error
	 * @param cause:              causa del error
	 * @param enableSuppression:  boolean enableSuppression
	 * @param writableStackTrace: boolean writableStackTrace
	 * 
	 */

	public AccesoDatosException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Lanza el mensaje de error de la excepción en la ejecución
	 * 
	 * @param message: mensaje de error
	 * @param cause:   causa del error
	 * 
	 */
	public AccesoDatosException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Lanza el mensaje de error de la excepción en la ejecución
	 * 
	 * @param message: mensaje de error
	 * 
	 */
	public AccesoDatosException(String message) {
		super(message);
	}

	/**
	 * Lanza el mensaje de error de la excepción en la ejecución
	 * 
	 * @param cause: causa del error
	 * 
	 */
	public AccesoDatosException(Throwable cause) {
		super(cause);
	}

}