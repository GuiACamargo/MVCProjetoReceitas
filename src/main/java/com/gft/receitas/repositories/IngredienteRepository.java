package com.gft.receitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.receitas.entities.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
	
	Ingrediente findByNome(String nome);
	List<Ingrediente> findByNomeContains(String nome);

}
