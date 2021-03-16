package com.ipartek.formacion.ejemplofinal.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el detalle (objeto Producto y cantidad) del carrito de la compra
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCarrito implements Serializable {
	/**
	 * Necesario para los elementos Serializables
	 */
	private static final long serialVersionUID = 8457880439062947651L;

	private Producto producto;
	private Integer cantidad;
}