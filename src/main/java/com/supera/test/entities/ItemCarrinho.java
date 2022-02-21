package com.supera.test.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_item_carrinho")
public class ItemCarrinho implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "{itemcarrinho.quantidade.not.null}")
	private Integer quantidade;
	private Double precoUnitario = 0.0;
	@ManyToOne(cascade = {CascadeType.MERGE})
	private Game game;
	
	public ItemCarrinho() {
	}
	
	public ItemCarrinho(Long id, Game game, Double precoUnitario, Integer quantidade) {
		this.id = id;
		this.game = game;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Double getPrecoTotal() {
		return getQuantidade() * getPrecoUnitario();
	}

	@Override
	public int hashCode() {
		return Objects.hash(game);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrinho other = (ItemCarrinho) obj;
		return Objects.equals(game, other.game);
	}
}
