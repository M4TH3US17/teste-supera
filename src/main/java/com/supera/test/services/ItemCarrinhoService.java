package com.supera.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supera.test.entities.ItemCarrinho;
import com.supera.test.repositories.ItemCarrinhoRepository;

@Service
public class ItemCarrinhoService {

	@Autowired
	private ItemCarrinhoRepository repository;
	
	public ItemCarrinho findById(Long id) {
		return repository.findById(id).get();
	}
	
	public ItemCarrinho save(ItemCarrinho obj) {
		return repository.save(obj);
	}
}
