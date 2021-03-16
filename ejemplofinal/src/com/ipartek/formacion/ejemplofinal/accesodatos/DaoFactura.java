package com.ipartek.formacion.ejemplofinal.accesodatos;

import com.ipartek.formacion.ejemplofinal.entidades.Factura;

/**
 * DAO para definir las operaciones de que no son CRUD para la entidad
 * Factura<br>
 * Interferimos la clase generica {@code Dao<Usuario>}
 * 
 * @author Jaime Quintana
 *
 */
public interface DaoFactura extends Dao<Factura> {

	String obtenerUltimoCodigo();
}
