package com.gft.receitas.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "receitas_id")
	public Receita receita;
	
	@ManyToOne
	@JoinColumn(name = "ingredientes_id")
	public Ingrediente ingrediente;
	
	@ManyToOne
	public UnidadeMedida unidadeMedida;
	
	public int quantidade;
	
	public Item() {
	}

	public Item(Receita receita, Ingrediente ingrediente,
			UnidadeMedida unidadeMedida) {
		super();
		this.receita = receita;
		this.ingrediente = ingrediente;
		this.unidadeMedida = unidadeMedida;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
