package com.ipartek.formacion.spring.springMF0226_3.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.PersistenceConstructor;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "alumno")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "codigo", "nombre", "apellidos" })
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nombre;
	private String apellidos;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "alumno") // , fetch = FetchType.LAZY)
	private final Set<Resenia> resenias = new HashSet<>();

	@PersistenceConstructor
	public Alumno(int codigo) {
		setCodigo(codigo);
	}

}
