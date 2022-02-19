package com.supera.test.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco implements Serializable {
private static final long serialVersionUID = 1L;

    @NotBlank(message = "{endereco.rua.not.blank}")
	private String rua;
	@NotNull(message = "{endereco.numcasa.not.null}")
	private Integer numCasa;
	
	public Endereco() {
	}

	public Endereco(String rua, Integer numCasa) {
		this.rua = rua;
		this.numCasa = numCasa;
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumCasa() {
		return numCasa;
	}

	public void setNumCasa(Integer numCasa) {
		this.numCasa = numCasa;
	}
}
