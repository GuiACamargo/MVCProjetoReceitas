package com.gft.receitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.entities.Ingrediente;
import com.gft.receitas.repositories.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }
	    str = str.toLowerCase().trim();
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public Ingrediente salvarIngrediente (Ingrediente ingrediente) {
		ingrediente.setNome(capitalize(ingrediente.getNome()));
		return ingredienteRepository.save(ingrediente);
	}
		
	public Ingrediente obterIngrediente (Long id) throws Exception{
		Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
		
		if(ingrediente.isEmpty()) {
			throw new Exception ("Ingrediente não encontrado!");
		}
		if (ingrediente.isPresent()) {
			return ingrediente.get();
		} else {
			return ingrediente.get();
		}
	}
	
	public List<Ingrediente> listaIngrediente(String nome) {
		if(nome != null) {
			return obterIngredientePeloNome(nome);
		}
		
		return listaIngredienteCompleto();
	}
	
	public List<Ingrediente> obterIngredientePeloNome (String nome) {
		return ingredienteRepository.findByNomeContains(nome);
	}
	
	public Ingrediente obterIngredientePuroPeloNome (String nome) {
		return ingredienteRepository.findByNome(nome);
	}
	
	public List<Ingrediente> listaIngredienteCompleto() {
		return ingredienteRepository.findAll();
	}
	
	public void excluirIngrediente (Long id) {
		ingredienteRepository.deleteById(id);
	}
}
