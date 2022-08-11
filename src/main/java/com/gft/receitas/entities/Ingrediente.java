package com.gft.receitas.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank (message = "Não pode ser vazio!")
	//@Column(unique = true)
	private String nome;
	@OneToMany(mappedBy = "ingrediente")
	public List<Item> item;

	public Ingrediente() {

	}

	public Ingrediente(@NotBlank(message = "Não pode ser vazio!") String nome, List<Item> item) {
		this.nome = nome;
		this.item = item;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}
