package com.supera.test.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant date;
	
	@ManyToOne
	private Cliente cliente;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemCarrinho> itens = new ArrayList<>();

	public Carrinho() {
	}

	public Carrinho(Long id, Cliente cliente) {
		this.id = id;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	public Instant getDate() {
		return date.now();
	}

	public Double getTotal() {
		double total = 0.0;
		for(ItemCarrinho item: itens) {
			total += item.getPrecoTotal();
		}
		return total + getFrete();
	}

	public Double getFrete() {
		int quantidade = 0;
		for(ItemCarrinho item: itens) {
			quantidade += item.getQuantidade();
		}
		double frete = (10.00 * quantidade);
		if(getSubTotal() >= 250.00) {
			return frete = 0.0;
		}
		return frete;
	}
	
	public Double getSubTotal() {
		double total = 0.0;
		for(ItemCarrinho item: itens) {
			total += item.getPrecoTotal();
		}
		return (Double) total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}
	
	public void addItem(ItemCarrinho item) {
		this.itens.add(item);
	}
	
	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		return Objects.equals(id, other.id);
	}
}
