package com.ipartek.formacion.mf0223Comidas.logicanegocio;

import com.ipartek.formacion.mf0223Comidas.entidades.Origen;

/**
 * Representa la lógica del negocio para el Origen del plato
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public interface ObtenerOrigenNegocio {
	Iterable<Origen> listadoOrigenes();

	Origen origenPorId(Long id);

}