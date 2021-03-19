package com.ipartek.formacion.mf0223Comidas.logicanegocio;

public class FabricaNegocio {

	private FabricaNegocio() {
	}

	private static final IntroducirReceta platoNegocio = new IntroducirRecetaImpl();
	private static final ObtenerCategoriaNegocio categoriaNegocio = new ObtenerCategoriaNegocioImpl();
	private static final ObtenerOrigenNegocio origenNegocio = new ObtenerOrigenNegocioImpl();
	private static final ObtenerDificultadNegocio dificultadNegocio = new ObtenerDificultadNegocioImpl();

	private static final RestaurarDatabase dataBase = new RestaurarDatabaseImpl();

	public static IntroducirReceta getPlatoNegocio() {
		return platoNegocio;
	}

	public static ObtenerCategoriaNegocio getCategoriaNegocio() {
		return categoriaNegocio;
	}

	public static ObtenerOrigenNegocio getOrigenNegocio() {
		return origenNegocio;
	}

	public static ObtenerDificultadNegocio getDificultadNegocio() {
		return dificultadNegocio;
	}

	public static RestaurarDatabase getDataBase() {
		return dataBase;
	}

}
