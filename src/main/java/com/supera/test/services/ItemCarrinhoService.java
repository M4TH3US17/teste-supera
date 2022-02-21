package com.supera.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supera.test.entities.ItemCarrinho;
import com.supera.test.repositories.ItemCarrinhoRepository;
import com.supera.test.services.exceptions.notfound.ItemCarrinhoNotFoundException;

@Service
public class ItemCarrinhoService {

	@Autowired
	private ItemCarrinhoRepository repository;
	
	public ItemCarrinho findById(Long id) throws ItemCarrinhoNotFoundException {
		return repository.findById(id).orElseThrow(
				() -> new ItemCarrinhoNotFoundException("ItemCompra com id "+id+" não existe."));
	}
	
	public ItemCarrinho save(ItemCarrinho obj) {
		return repository.save(obj);
	}
}
