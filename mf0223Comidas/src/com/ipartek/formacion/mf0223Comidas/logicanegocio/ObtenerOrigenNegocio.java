package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Origen;

public interface ObtenerOrigenNegocio {
	Iterable<Origen> listadoOrigenes();

	Origen origenPorId(Long id);

}