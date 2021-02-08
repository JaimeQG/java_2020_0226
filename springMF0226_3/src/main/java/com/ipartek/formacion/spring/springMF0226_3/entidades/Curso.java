package com.ipartek.formacion.spring.springMF0226_3.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "codigo", "nombre", "identificador", "nHoras" })
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nombre;
	private String identificador;
	private int nHoras;

	@ManyToOne // (fetch = FetchType.EAGER)
	private Profesor profesor;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "curso")
	private final Set<Resenia> resenias = new HashSet<>();

	public Curso(int codigo) {
		setCodigo(codigo);
	}

	public Curso(int codigo, String nombre, String identificador, int nHoras) {
		setCodigo(codigo);
		setNombre(nombre);
		setIdentificador(identificador);
		setNHoras(nHoras);
	}

	public void setProfesor(Profesor profesor) {

		this.profesor = profesor;
	}
}