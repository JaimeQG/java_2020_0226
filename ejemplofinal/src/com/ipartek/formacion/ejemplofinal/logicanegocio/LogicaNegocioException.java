package com.ipartek.formacion.ejemplofinal.logicanegocio;

/**
 * Representa la captura de los errores en tiempo de ejecución para las
 * implentaciones de la lógica de negocio
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public class LogicaNegocioException extends RuntimeException {

	private static final long serialVersionUID = -6177389577332893172L;

	/**
	 * Constructor vacío de la clase
	 * 
	 */
	public LogicaNegocioException() {
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
	public LogicaNegocioException(String message, Throwable cause, boolean enableSuppression,
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
	public LogicaNegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Lanza el mensaje de error de la excepción en la ejecución
	 * 
	 * @param message: mensaje de error
	 * 
	 */
	public LogicaNegocioException(String message) {
		super(message);
	}

	/**
	 * Lanza el mensaje de error de la excepción en la ejecución
	 * 
	 * @param cause: causa del error
	 * 
	 */
	public LogicaNegocioException(Throwable cause) {
		super(cause);
	}

}
