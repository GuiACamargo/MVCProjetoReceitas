package com.gft.receitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.receitas.entities.Item;
import com.gft.receitas.entities.Receita;

interface ReceitaId{
	String getReceita();
}

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
		
	Long findByIdContains(Long id);
	List<Item> findByReceitaNomeContains(String nome);
	List<Item> findByIngredienteNomeContains(String nome);
	List<Item> findByReceita(Receita receita);
	
}
