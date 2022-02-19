package com.supera.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supera.test.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
