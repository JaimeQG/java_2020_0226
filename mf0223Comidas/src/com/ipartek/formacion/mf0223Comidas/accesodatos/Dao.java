package com.ipartek.formacion.mf0223Comidas.accesodatos;

/**
 * DAO para definir las operaciones de CRUD con una Clase Generica, en este caso
 * hemos usado una T de Pojo/entidad/objeto
 * 
 * @author Jaime Quintana
 * @version 1.0
 *
 */
public interface Dao<T> {
	/**
	 * lista de Pojos/entidad/objetos
	 * 
	 * @return todos los pojos, si no existe ninguno {@code Set<T>} vacio no null
	 * 
	 */
	default Iterable<T> obtenerTodos() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	/**
	 * Recupera el detalle de un Pojo/entidad/objeto
	 * 
	 * @param id identificador
	 * @throws AccesoDatosException si el nombre del Pojo/entidad/objeto ya existe
	 * @return Pojo/entidad/objeto con sus datos o null si no encuentra por su id
	 */
	default T obtenerPorId(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	/**
	 * Crea un nuevo Pojo/entidad/objeto y cuando es guardado se le asigan una nueva
	 * id
	 * 
	 * @param objeto T con los datos a guardar
	 * @throws AccesoDatosException si el nombre del Pojo/entidad/objeto ya existe
	 * @return pojo con su id actualizado
	 */
	default T insertar(T objeto) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	/**
	 * Modifica un Pojo/entidad/objeto
	 * 
	 * @param objeto T con los datos nuevos
	 * @throws AccesoDatosException si no encuentra el Pojo/entidad/objeto
	 * @return T objeto modificado
	 *
	 */
	default T modificar(T objeto) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	/**
	 * Elimina el Pojo/entidad/objeto gracias a su identificador
	 * 
	 * @param id identificador
	 * @throws AccesoDatosException si no encuentra el Pojo/entidad/objeto
	 */
	default void borrar(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
}