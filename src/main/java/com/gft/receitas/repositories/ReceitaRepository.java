package com.gft.receitas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.receitas.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	
	List<Receita> findByItemIngredienteNomeContains(String ingrediente);

	Optional<Receita> findById(Long id);
	List<Receita> findByNomeContains(String nome);
}
