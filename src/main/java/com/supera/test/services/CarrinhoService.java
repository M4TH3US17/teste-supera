package com.supera.test.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.supera.test.entities.Carrinho;
import com.supera.test.entities.ItemCarrinho;
import com.supera.test.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private GameService gameService;
	@Autowired
	private ItemCarrinhoService itemCarrinhoService;

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
		carrinho.setCliente(clienteService.findById(id));

		Set<ItemCarrinho> list = new HashSet<>();
		obj.getItens().forEach(game -> {
			ItemCarrinho item = itemCarrinhoService.save(new ItemCarrinho(
					null, gameService.findById(game.getGame().getId()),
					gameService.findById(game.getGame().getId()).getPreco(),
					game.getQuantidade()));
			
			list.add(item);
		});
		carrinho.setItens(new ArrayList<>(list));
		return repository.save(carrinho);
	}

	@Modifying
	@Transactional
	public Carrinho update(Carrinho obj, Long id) {
		Carrinho carrinho = findById(id);
		carrinho.setCliente(clienteService.findById(carrinho.getCliente().getId()));
		carrinho.setItens(obj.getItens());
		
		updateData(carrinho, obj);
		return repository.save(carrinho);
	}

	private void updateData(Carrinho entity, Carrinho obj) {
		entity.setItens(new ArrayList<>());
		
		Set<ItemCarrinho> list = new HashSet<>();
		obj.getItens().forEach(game -> {
			ItemCarrinho item = new ItemCarrinho(
					game.getId(), gameService.findById(game.getGame().getId()),
					gameService.findById(game.getGame().getId()).getPreco(),
					game.getQuantidade());
			
			list.add(item);
		});
		entity.setItens(new ArrayList<>(list));
	}

}
