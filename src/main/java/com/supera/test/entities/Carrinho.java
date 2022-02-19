package com.supera.test.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Transient
	private Double frete = 10.00;
	@Transient
	private Integer quantidade = 0;
	@OneToOne
	private Cliente cliente;
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name = "id_order"),
			inverseJoinColumns = @JoinColumn(name = "id_game"))
	private Set<Game> itens = new HashSet<>();

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
	public Instant getDataCompra() { // Adiciona a data de pedido automaticamente
		return Instant.now();
	}

	/*public Double getFrete() { // para cada produto será somado 10 ao frete
		//items.forEach(x -> {quantity += x.getQuantity();});
		return frete * quantidade;
	}
	
	public Double getTotal() { // calcular o total (frete + preço de cada produto)
		double soma = 0.0;
		
		for(Game x: itens) {
			//soma += (x.getPreco()  x.getQuantity());
		}
		return soma + getFrete();
	}
	
	public Double getSubTotal() {
		return getTotal() - getFrete();
	}*/
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Game> getItens() {
		return itens;
	}

	public void addItem(Game game) {
		this.itens.add(game);
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
