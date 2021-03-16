package com.ipartek.formacion.ejemplofinal.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el detalle de la factura de la compra (cantidad de producto y
 * objetos: Factura y Producto).
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFactura implements Serializable {
	/**
	 * Necesario para los elementos Serializables
	 */
	private static final long serialVersionUID = 3924664355341140287L;

	private Factura factura;
	private Producto producto;
	private Integer cantidad;

	public BigDecimal getTotal() {
		return producto.getPrecio().multiply(new BigDecimal(cantidad));
	}
}
