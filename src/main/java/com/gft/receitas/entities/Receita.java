package com.gft.receitas.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
public class Receita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank (message = "Nome do Drink não pode ser vazio!")
	@Column(unique = true)
	private String nome;
	@Min(value = 1, message = "Tempo de Preparo não deveria ser menor que 1 minuto!")
    @Max(value = 999, message = "Tempo de Preparo não deveria ser maior que 999 minutos!")
	@Positive(message = "Tempo de Preparo deve ser positivo!")
	private int tempoDePreparo;
	@NotBlank (message = "Modo de Preparo não pode ser vazio!")
	@Size(min = 1, max = 2000, message = "Modo de Preparo é no mínimo 1 caracter e no máximo 2000 caracteres")
	private String modoDePreparo;
	@NotNull(message="Escolha o tipo do Drink!")
	private Boolean alcoolico;
	@OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "receita")
	private List<Item> item;
	@NotBlank (message = "Itens não podem ser vazios!")
	private String info;
	@Column(name="formatado")
	private String infoFormatado;
		
	public Receita() {
	}

	public Receita(String nome, int tempoDePreparo, String modoDePreparo, boolean alcoolico, List<Item> item) {
		this.nome = nome;
		this.tempoDePreparo = tempoDePreparo;
		this.modoDePreparo = modoDePreparo;
		this.alcoolico = alcoolico;
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
	
	public int getTempoDePreparo() {
		return tempoDePreparo;
	}
	
	public void setTempoDePreparo(int tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}
	
	public String getModoDePreparo() {
		return modoDePreparo;
	}
	
	public void setModoDePreparo(String modoDePreparo) {
		this.modoDePreparo = modoDePreparo;
	}

	public Boolean getAlcoolico() {
		return alcoolico;
	}

	public void setAlcoolico(Boolean alcoolico) {
		this.alcoolico = alcoolico;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfoFormatado() {
		return infoFormatado;
	}

	public void setInfoFormatado(String infoFormatado) {
		this.infoFormatado = infoFormatado;
	}
}
