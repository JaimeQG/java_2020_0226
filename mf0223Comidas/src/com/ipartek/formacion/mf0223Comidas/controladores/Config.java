package com.ipartek.formacion.mf0223Comidas.controladores;

import com.ipartek.formacion.mf0223Comidas.logicanegocio.FabricaNegocio;
import com.ipartek.formacion.mf0223Comidas.logicanegocio.IntroducirReceta;
import com.ipartek.formacion.mf0223Comidas.logicanegocio.ObtenerCategoriaNegocio;
import com.ipartek.formacion.mf0223Comidas.logicanegocio.ObtenerOrigenNegocio;
import com.ipartek.formacion.mf0223Comidas.logicanegocio.RestaurarDatabase;

class Config {

	private Config() {
	}

	static final String PATH_VISTAS = "/WEB-INF/vistas/";

	static final String UPLOAD_DIRECTORY = "database_backup";

	static final IntroducirReceta platoNegocio = FabricaNegocio.getPlatoNegocio();
	static final ObtenerCategoriaNegocio categoriaNegocio = FabricaNegocio.getCategoriaNegocio();
	static final ObtenerOrigenNegocio origenNegocio = FabricaNegocio.getOrigenNegocio();
	static final RestaurarDatabase dataBase = FabricaNegocio.getDataBase();
}
