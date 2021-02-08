package com.ipartek.formacion.spring.springMF0226_3.entidades;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resenia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "codigo", "comentario", "fecha" })
public class Resenia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String comentario;
	private LocalDate fecha;

	@ManyToOne // (fetch = FetchType.EAGER)
	private Curso curso;

	@ManyToOne
	private Alumno alumno;

	public Resenia(int codigo, String comentario, LocalDate fecha) {
		setCodigo(codigo);
		setComentario(comentario);
		setFecha(fecha);
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
