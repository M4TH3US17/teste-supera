package com.supera.test.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String street;
	private Integer numberHouse;
	
	public Endereco() {
	}

	public Endereco(String street, Integer numberHouse) {
		this.street = street;
		this.numberHouse = numberHouse;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(Integer numberHouse) {
		this.numberHouse = numberHouse;
	}
}
