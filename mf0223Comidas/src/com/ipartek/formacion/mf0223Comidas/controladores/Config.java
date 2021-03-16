package com.ipartek.formacion.mf0223Comidas.controladores;

import com.ipartek.formacion.mf0223Comidas.logicanegocio.CategoriaNegocio;
import com.ipartek.formacion.mf0223Comidas.logicanegocio.FabricaNegocio;
import com.ipartek.formacion.mf0223Comidas.logicanegocio.OrigenNegocio;
import com.ipartek.formacion.mf0223Comidas.logicanegocio.PlatoNegocio;

class Config {

	private Config() {
	}

	static final String PATH_VISTAS = "/WEB-INF/vistas/";

	static final PlatoNegocio platoNegocio = FabricaNegocio.getPlatoNegocio();
	static final CategoriaNegocio categoriaNegocio = FabricaNegocio.getCategoriaNegocio();
	static final OrigenNegocio origenNegocio = FabricaNegocio.getOrigenNegocio();
}
