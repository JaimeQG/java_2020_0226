package com.ipartek.formacion.mf0223Comidas.logicanegocio;

/**
 * Fabrica Negocio para las clases de negocio: IntroducirReceta,
 * ObtenerCategoriaNegocio, ObtenerOrigenNegocio, ObtenerDificultadNegocio y
 * RestaurarDatabase
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public class FabricaNegocio {

	private FabricaNegocio() {
	}

	private static final IntroducirReceta platoNegocio = new IntroducirRecetaImpl();
	private static final ObtenerCategoriaNegocio categoriaNegocio = new ObtenerCategoriaNegocioImpl();
	private static final ObtenerOrigenNegocio origenNegocio = new ObtenerOrigenNegocioImpl();
	private static final ObtenerDificultadNegocio dificultadNegocio = new ObtenerDificultadNegocioImpl();

	private static final RestaurarDatabase dataBase = new RestaurarDatabaseImpl();

	/**
	 * Obtener objeto de la clase IntroducirReceta
	 * 
	 * @return objeto IntroducirReceta
	 */
	public static IntroducirReceta getPlatoNegocio() {
		return platoNegocio;
	}

	/**
	 * Obtener objeto de la clase ObtenerCategoriaNegocio
	 * 
	 * @return objeto ObtenerCategoriaNegocio
	 */
	public static ObtenerCategoriaNegocio getCategoriaNegocio() {
		return categoriaNegocio;
	}

	/**
	 * Obtener objeto de la clase ObtenerOrigenNegocio
	 * 
	 * @return objeto ObtenerOrigenNegocio
	 */
	public static ObtenerOrigenNegocio getOrigenNegocio() {
		return origenNegocio;
	}

	/**
	 * Obtener objeto de la clase ObtenerDificultadNegocio
	 * 
	 * @return objeto ObtenerDificultadNegocio
	 */
	public static ObtenerDificultadNegocio getDificultadNegocio() {
		return dificultadNegocio;
	}

	/**
	 * Obtener objeto de la clase RestaurarDatabase
	 * 
	 * @return objeto RestaurarDatabase
	 */
	public static RestaurarDatabase getDataBase() {
		return dataBase;
	}

}
