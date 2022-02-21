package com.supera.test.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.supera.test.entities.Game;
import com.supera.test.repositories.GameRepository;
import com.supera.test.services.exceptions.notfound.GameNotFoundException;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;
	
	public List<Game> findAll(){
		return repository.findAll();
	}
	
	public Game findById(Long id) throws GameNotFoundException {
		Game obj = repository.findById(id)
				.orElseThrow(() -> new GameNotFoundException("Game com id " + id + " não foi encontrado."));
		return obj;
	}
	
	@Transactional
	public Game insert(Game game) {
		return repository.save(game);
	}
	
	@Transactional
	public void deleteById(Long id) throws GameNotFoundException {
		if(repository.existsById(id) == false) {
			throw new  GameNotFoundException("Game com id " + id + " não foi encontrado.");
		}
		repository.deleteById(id);
	}
	
	@Modifying
	@Transactional
	public Game update(Game game, Long id) throws GameNotFoundException {
		if(repository.existsById(id) == false) {
			throw new  GameNotFoundException("Game com id " + id + " não foi encontrado.");
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
