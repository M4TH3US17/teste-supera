package com.supera.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supera.test.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

	@Query("Select g From Game g ORDER BY g.nome DESC")
	List<Game> findAllOrderByNome();
	
	@Query("SELECT g FROM Game AS g ORDER BY g.preco ASC")
	List<Game> findAllOrderByPrecoAsc();
	
	@Query("SELECT g FROM Game AS g ORDER BY g.preco DESC")
	List<Game> findAllOrderByPrecoDesc();
}
