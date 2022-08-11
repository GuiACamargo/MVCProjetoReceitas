package com.gft.receitas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.receitas.entities.UnidadeMedida;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long> {
	
	UnidadeMedida findByNome(String nome);
	
	List<UnidadeMedida> findByNomeContains(String nome);

}
