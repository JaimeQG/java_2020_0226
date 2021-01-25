package com.ipartek.formacion.euromillones.modelos;

import java.io.Serializable;
import java.time.LocalDate;

public class Sorteo implements Serializable {

	private static final long serialVersionUID = -8083279652527759956L;

	private Long id;
	private String numero_1, numero_2, numero_3, numero_4, numero_5;
	private String estrella_1, estrella_2;
	private LocalDate fechaSorteo;

	public Sorteo(Long id, String numero_1, String numero_2, String numero_3, String numero_4, String numero_5,
			String estrella_1, String estrella_2, LocalDate fechaSorteo) {

		setId(id);
		setNumero_1(numero_1);
		setNumero_2(numero_2);
		setNumero_3(numero_3);
		setNumero_4(numero_4);
		setNumero_5(numero_5);
		setEstrella_1(estrella_1);
		setEstrella_2(estrella_2);
		setFechaSorteo(fechaSorteo);
	}

	public Sorteo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero_1() {
		return numero_1;
	}

	public void setNumero_1(String numero_1) {
		this.numero_1 = numero_1;
	}

	public String getNumero_2() {
		return numero_2;
	}

	public void setNumero_2(String numero_2) {
		this.numero_2 = numero_2;
	}

	public String getNumero_3() {
		return numero_3;
	}

	public void setNumero_3(String numero_3) {
		this.numero_3 = numero_3;
	}

	public String getNumero_4() {
		return numero_4;
	}

	public void setNumero_4(String numero_4) {
		this.numero_4 = numero_4;
	}

	public String getNumero_5() {
		return numero_5;
	}

	public void setNumero_5(String numero_5) {
		this.numero_5 = numero_5;
	}

	public String getEstrella_1() {
		return estrella_1;
	}

	public void setEstrella_1(String estrella_1) {
		this.estrella_1 = estrella_1;
	}

	public String getEstrella_2() {
		return estrella_2;
	}

	public void setEstrella_2(String estrella_2) {
		this.estrella_2 = estrella_2;
	}

	public LocalDate getFechaSorteo() {
		return fechaSorteo;
	}

	public void setFechaSorteo(LocalDate fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}

	@Override
	public String toString() {
		return "Sorteo [id=" + id + ", numero_1=" + numero_1 + ", numero_2=" + numero_2 + ", numero_3=" + numero_3
				+ ", numero_4=" + numero_4 + ", numero_5=" + numero_5 + ", estrella_1=" + estrella_1 + ", estrella_2="
				+ estrella_2 + ", fechaSorteo=" + fechaSorteo + "]";
	}

}
