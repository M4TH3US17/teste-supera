package com.supera.test.services;

import java.util.HashSet;
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
	private ClienteService clienteService;
	@Autowired
	private GameService gameService;

	public List<Carrinho> findAll() {
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
		Carrinho carrinho = obj;
		obj.setCliente(clienteService.findById(id));
		repository.save(carrinho);
		obj.getItens().forEach(item -> {
			carrinho.getItens().add(gameService.findById(item.getId()));
		});
		return repository.save(carrinho);
	}

	@Modifying
	@Transactional
	public Carrinho update(Carrinho obj, Long id) {
		Carrinho carrinho = findById(id);
		carrinho.setCliente(
				clienteService.findById(carrinho.getCliente().getId()));
		
		updateData(carrinho, obj);
		return repository.save(carrinho);
	}

	private void updateData(Carrinho entity, Carrinho obj) {
		entity.getItens(new HashSet<>());
		obj.getItens().forEach(item -> {
			entity.getItens().add(gameService.findById(item.getId()));
		});
	}

}
