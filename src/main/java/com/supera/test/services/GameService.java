package com.supera.test.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.supera.test.entities.Game;
import com.supera.test.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;
	
	public List<Game> findAll(){
		return repository.findAll();
	}
	
	public Game findById(Long id) {
		Optional<Game> obj = repository.findById(id);
		return obj.get();
	}
	
	@Transactional
	public Game insert(Game game) {
		return repository.save(game);
	}
	
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Modifying
	@Transactional
	public Game update(Game game, Long id) {
		if(repository.existsById(id) == false) {
			// disparar uma execeção notfound
		}
		Game entity = repository.getById(id);
		updateData(entity, game);
		return repository.save(entity);
	}
	
	private void updateData(Game entity, Game obj) {
		entity.setDescricao(obj.getDescricao());
		entity.setNome(obj.getNome());
		entity.setPreco(obj.getPreco());
	}
}
