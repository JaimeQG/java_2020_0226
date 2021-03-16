package com.ipartek.formacion.ejemplofinal.logicanegocio;

import java.util.Set;

import com.ipartek.formacion.ejemplofinal.entidades.Factura;
import com.ipartek.formacion.ejemplofinal.entidades.Producto;

/**
 * Representa la lógica del negocio para el carrito de la compra
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
public interface CarritoNegocio {
	Set<Producto> listadoProductos();

	Producto productoPorId(Long id);

	Factura guardarFactura(Factura factura);
}