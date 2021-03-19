package com.ipartek.formacion.mf0223Comidas.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el plato de comida
 * 
 * @author Jaime Quintana
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plato implements Serializable {

	private static final long serialVersionUID = -8338549901842091017L;

	private Long id;
	private String nombre;
	private String preparacion;
	private int tiempo;
	private int calorias;

	private Categoria categoria;

	private Origen origen;

	private Dificultad dificultad;

	private boolean correcto = true;

	private String errorId;
	private String errorNombre;
	private String errorPreparacion;
	private String errorDificultad;
	private String errorTiempo;
	private String errorCalorias;

	private String errorCategoria;
	private String errorOrigen;

	public Plato(Long id, String nombre, String preparacion, int tiempo, int calorias, Categoria categoria,
			Origen origen, Dificultad dificultad) {
		setId(id);
		setNombre(nombre);
		setPreparacion(preparacion);
		setTiempo(tiempo);
		setCalorias(calorias);
		setCategoria(categoria);
		setOrigen(origen);
		setDificultad(dificultad);
	}

	public Plato(String id, String nombre, String preparacion, String tiempo, String calorias) {

		setId(id);
		setNombre(nombre);
		setPreparacion(preparacion);
		setTiempo(tiempo);
		setCalorias(calorias);
	}

	// correcto
	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	// Id
	private void setId(String id) {
		try {
			setId(id.trim().length() != 0 ? Long.parseLong(id) : null);
		} catch (NumberFormatException e) {
			setErrorId("El id debe ser numérico");
		}
	}

	public void setId(Long id) {
		if (id != null && id <= 0) {
			setErrorId("No se admiten ids inferiores o iguales a 0");
		}
		this.id = id;
	}

	public void setErrorId(String errorId) {
		correcto = false;
		this.errorId = errorId;
	}

	// Nombre
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().length() < 3 || !nombre.matches("\\p{Lu}\\p{Ll}{2}[\\p{Ll} ]*")) {
			setErrorNombre("Debe introducir un nombre con solo letras y mayúscula la primera. Mínimo 3 caracteres");
		}
		this.nombre = nombre;
	}

	public void setErrorNombre(String errorNombre) {
		correcto = false;
		this.errorNombre = errorNombre;
	}

	// Preparación
	public void setPreparacion(String preparacion) {
		if (preparacion == null || preparacion.trim().length() == 0) {
			setErrorPreparacion("La preparación del plato es obligatoria");
		}
		this.preparacion = preparacion;
	}

	public void setErrorPreparacion(String errorPreparacion) {
		correcto = false;
		this.errorPreparacion = errorPreparacion;
	}

	// Dificultad
	public void setDificultad(Dificultad dificultad) {
		if (dificultad == null || dificultad.getId() == null || dificultad.getId() == 0L) {
			setErrorDificultad("La dificultad del plato es obligatoria");
		}
		this.dificultad = dificultad;
	}

	public void setErrorDificultad(String errorDificultad) {
		correcto = false;
		this.errorDificultad = errorDificultad;
	}

	// Tiempo
	private void setTiempo(String tiempo) {
		try {
			setTiempo(Integer.parseInt(tiempo));
		} catch (Exception e) {
			setErrorTiempo("El tiempo de elaboración del plato deben ser un número");
		}
	}

	public void setTiempo(Integer tiempo) {
		if (tiempo == null || tiempo < 0) {
			setErrorTiempo("El tiempo debe ser mayor o igual a cero");
		}
		this.tiempo = tiempo;
	}

	public void setErrorTiempo(String errorTiempo) {
		correcto = false;
		this.errorTiempo = errorTiempo;
	}

	// Calorias
	private void setCalorias(String calorias) {
		try {
			setCalorias(Integer.parseInt(calorias));
		} catch (Exception e) {
			setErrorCalorias("Las calorias deben ser un número");
		}
	}

	public void setCalorias(Integer calorias) {
		if (calorias == null || calorias < 0) {
			setErrorCalorias("Las calorias debe ser mayor o igual a cero");
		}
		this.calorias = calorias;
	}

	public void setErrorCalorias(String errorCalorias) {
		correcto = false;
		this.errorCalorias = errorCalorias;
	}

	// Categoría
	public void setCategoria(Categoria categoria) {
		if (categoria == null || categoria.getId() == null || categoria.getId() == 0L) {
			setErrorCategoria("La categoría del plato es obligatoria");
		}
		this.categoria = categoria;
	}

	public void setErrorCategoria(String errorCategoria) {
		correcto = false;
		this.errorCategoria = errorCategoria;
	}

	// Origen
	public void setOrigen(Origen origen) {
		if (origen == null || origen.getId() == null || origen.getId() == 0L) {
			setErrorOrigen("El origen del plato es obligatorio");
		}
		this.origen = origen;
	}

	public void setErrorOrigen(String errorOrigen) {
		correcto = false;
		this.errorOrigen = errorOrigen;
	}

	@Override
	public String toString() {
		return "Plato [id=" + id + ", nombre=" + nombre + ", calorias=" + calorias + ", categoria=" + categoria
				+ ", origen=" + origen + "]";
	}

}