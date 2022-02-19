package com.supera.test.controllers;

import java.util.List;

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

import com.supera.test.entities.Carrinho;
import com.supera.test.services.CarrinhoService;

@RestController
@RequestMapping("/api/carrinhos")
public class CarrinhoController {

	@Autowired 
	private CarrinhoService service;
	
	@GetMapping
	public ResponseEntity<List<Carrinho>> findAll() {
		List<Carrinho> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Carrinho> findById(@PathVariable Long id) {
		Carrinho obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping(value = "save/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Carrinho> save(@RequestBody Carrinho order, @PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(order, id));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Carrinho> update(@RequestBody Carrinho obj, @PathVariable Long id){
		return ResponseEntity.ok().body(service.update(obj, id));
	}
}
