package com.ipartek.formacion.mf0223Comidas.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plato {

	private Long id;
	private String nombre;
	private int calorias;

	private Categoria categoria;

	private Origen origen;

	private boolean correcto = true;

	private String errorId;
	private String errorNombre;
	private String errorCalorias;

	private String errorCategoria;
	private String errorOrigen;

	public Plato(Long id, String nombre, int calorias, Categoria categoria, Origen origen) {
		setId(id);
		setNombre(nombre);
		setCalorias(calorias);
		setCategoria(categoria);
		setOrigen(origen);
	}

	public Plato(String id, String nombre, String calorias) {

		setId(id);
		setNombre(nombre);
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