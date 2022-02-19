package com.supera.test.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.supera.test.entities.Carrinho;
import com.supera.test.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;
	@Autowired
	private ClienteService clientService;
	@Autowired
	private GameService gameService;
	
	public List<Carrinho> findAll(){
		return repository.findAll();
	}
	
	public Carrinho findById(Long id) {
		Optional<Carrinho> obj = repository.findById(id);
		return obj.get();
	}
	
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public Carrinho save(Carrinho obj, Long id) {
		Carrinho order = obj;
		obj.setCliente(clientService.findById(id));
		repository.save(order);
		obj.getItens().forEach(item -> {
			order.getItens().add(gameService.findById(item.getId()));
		});
		return repository.save(order);
	}
	
	@Modifying
	@Transactional
	public Carrinho update(Carrinho obj, Long id) {
		Carrinho entity = repository.findById(id)
				.orElse(new Carrinho());
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	private void updateData(Carrinho entity, Carrinho obj) {
		entity.getItens().forEach(item -> {
			entity.getItens().add(gameService.findById(item.getId()));
		});
	}
	
}
