package com.supera.test.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supera.test.entities.Game;
import com.supera.test.services.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

	@Autowired
	private GameService service;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Game>> findAll(){
		List<Game> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Game> findById(@PathVariable Long id) throws Exception {
		Game obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Game> insert(@Valid @RequestBody Game game){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(game));
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping(value = "{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Game> insert(@Valid @RequestBody Game game, @PathVariable Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.update(game, id));
	}
}
