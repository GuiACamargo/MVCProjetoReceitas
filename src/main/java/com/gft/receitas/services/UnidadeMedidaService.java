package com.gft.receitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.entities.UnidadeMedida;
import com.gft.receitas.repositories.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {
	
	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }
	    str = str.toLowerCase().trim();
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public UnidadeMedida salvarUnidadeMedida (UnidadeMedida unidadeMedida) {
		unidadeMedida.setNome(capitalize(unidadeMedida.getNome()));
		return unidadeMedidaRepository.save(unidadeMedida);
	}
	
	public UnidadeMedida obterUnidadeMedida (Long id) throws Exception{
		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);
		
		if (unidadeMedida.isEmpty()) {
			throw new Exception ("Unidade de Medida não encontrada!");
		}
		
		return unidadeMedida.get();
	}
	
	public UnidadeMedida obterUnidadeMedidaPuroPeloNome (String nome) {
		return unidadeMedidaRepository.findByNome(nome);
	}
	
	public List<UnidadeMedida> listaUnidadeMedida (String nome) {
		if (nome != null) {
			return unidadeMedidaRepository.findByNomeContains(nome);
		}
		
		return listaUnidadeMedidaCompleto();
	}
	
	public List<UnidadeMedida> listaUnidadeMedidaCompleto() {
		return unidadeMedidaRepository.findAll();
	}
	
	public void excluirUnidadeMedida(Long id) {
		unidadeMedidaRepository.deleteById(id);
	}
}
