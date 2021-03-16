package com.ipartek.formacion.mf0223Comidas.logicanegocio;

public class FabricaNegocio {

	private FabricaNegocio() {
	}

	private static final PlatoNegocio platoNegocio = new PlatoNegocioImpl();
	private static final CategoriaNegocio categoriaNegocio = new CategoriaNegocioImpl();
	private static final OrigenNegocio origenNegocio = new OrigenNegocioImpl();

	public static PlatoNegocio getPlatoNegocio() {
		return platoNegocio;
	}

	public static CategoriaNegocio getCategoriaNegocio() {
		return categoriaNegocio;
	}

	public static OrigenNegocio getOrigenNegocio() {
		return origenNegocio;
	}

}
