package com.ipartek.formacion.ejemplofinal.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Representa la factura de la compra hecha en el supermercado.
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura implements Serializable {

	private static final BigDecimal IVA = new BigDecimal("0.21");

	/**
	 * Necesario para los elementos Serializables
	 */
	private static final long serialVersionUID = 2396176411731906644L;

	private Long id;
	private String codigo;
	private LocalDate fecha;

	private Cliente cliente;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<DetalleFactura> detallesFactura = new HashSet<>();

	/**
	 * Calcula el importe total de la factura (sumando los detalles/productos) sin
	 * IVA
	 * 
	 * @return total Total de la factura sin IVA
	 * 
	 */
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (DetalleFactura detalle : detallesFactura) {
			total = total.add(detalle.getTotal());
		}

		return total;
	}

	/**
	 * Calcula el importe del IVA la factura
	 * 
	 * @return total del IVA
	 * 
	 */
	public BigDecimal getIva() {
		return getTotal().multiply(IVA);
	}

	/**
	 * Calcula el importe total de la Factura (con IVA incluido)
	 * 
	 * @return total de la factura + IVA
	 * 
	 */
	public BigDecimal getTotalConIva() {
		return getTotal().add(getIva());
	}
}