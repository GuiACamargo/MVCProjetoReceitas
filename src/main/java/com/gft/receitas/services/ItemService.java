package com.gft.receitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.receitas.entities.Item;
import com.gft.receitas.entities.Receita;
import com.gft.receitas.repositories.ItemRepository;
import com.gft.receitas.repositories.ReceitaRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ReceitaRepository receitaRepository;
	
	public Item salvarItem(Item item) {
		return itemRepository.save(item);
	}
	
	public Item obterItem (Long id) throws Exception {
		Optional<Item> item = itemRepository.findById(id);
		
		if(item.isEmpty()) {
			throw new Exception ("Item não encontrada!");
		}
		
		if (item.isPresent()) {
			return item.get();
		} else {
			return item.get();
		}
	}
	
	public List<Item> listaItensCompletos() {
		return itemRepository.findAll();
	}
	
	public void excluirItem (Long id) {
		itemRepository.deleteById(id);
	}
	
	public Long acharId (Long id) {
		return itemRepository.findByIdContains(id);
	}
	
	public void salvarReceitaNoItem (Item item, Receita receita) {
		item.setReceita(receita);
	}
	
	public List<Item> listaItensPelaReceita (Receita receita) {
		return itemRepository.findByReceita(receita);
	}
}
