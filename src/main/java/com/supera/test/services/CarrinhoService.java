package com.supera.test.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.supera.test.entities.Carrinho;
import com.supera.test.entities.ItemCarrinho;
import com.supera.test.repositories.CarrinhoRepository;
import com.supera.test.services.exceptions.notfound.CarrinhoNotFoundException;
import com.supera.test.services.exceptions.notfound.ClienteNotFoundException;
import com.supera.test.services.exceptions.notfound.GameNotFoundException;
import com.supera.test.services.exceptions.notfound.ItemCarrinhoNotFoundException;

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

	public Carrinho findById(Long id) throws CarrinhoNotFoundException {
		Carrinho obj = repository.findById(id)
				.orElseThrow(() -> new CarrinhoNotFoundException("Carrinho com id "+id+" não foi encontrado."));
		return obj;
	}

	@Transactional
	public void deleteById(Long id) throws CarrinhoNotFoundException {
		if(repository.existsById(id) == false) {
			throw new CarrinhoNotFoundException("Carrinho com id "+id+" não foi encontrado.");
		}
		repository.deleteById(id);
	}

	@Transactional
	public Carrinho save(Carrinho obj, Long id) throws CarrinhoNotFoundException, 
	ClienteNotFoundException, ItemCarrinhoNotFoundException {
		Carrinho carrinho = obj;
		carrinho.setCliente(clienteService.findById(id));

		Set<ItemCarrinho> list = new HashSet<>();
		obj.getItens().forEach(game -> {
			try {
			ItemCarrinho item = itemCarrinhoService.save(new ItemCarrinho(
					null, gameService.findById(game.getGame().getId()),
					gameService.findById(game.getGame().getId()).getPreco(),
					game.getQuantidade()));
			
			list.add(item);
		} catch(GameNotFoundException e) {}
		 });
		carrinho.setItens(new ArrayList<>(list));
		return repository.save(carrinho);
	}

	@Modifying
	@Transactional
	public Carrinho update(Carrinho obj, Long id) throws CarrinhoNotFoundException,
	ClienteNotFoundException, ItemCarrinhoNotFoundException {
		Carrinho carrinho = findById(id);
		carrinho.setCliente(clienteService.findById(carrinho.getCliente().getId()));
		carrinho.setItens(obj.getItens());
		
		updateData(carrinho, obj);
		return repository.save(carrinho);
	}

	@Transactional
	private void updateData(Carrinho entity, Carrinho obj) {
		entity.setItens(new ArrayList<>());
		
		Set<ItemCarrinho> list = new HashSet<>();
		obj.getItens().forEach(game -> {
			try {
			ItemCarrinho item = new ItemCarrinho(
					game.getId(), gameService.findById(game.getGame().getId()),
					gameService.findById(game.getGame().getId()).getPreco(),
					game.getQuantidade());
			
			list.add(item);
			} catch(GameNotFoundException e){
			}
		});
		entity.setItens(new ArrayList<>(list));
	}

}
