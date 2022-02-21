package com.supera.test.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.supera.test.entities.Cliente;
import com.supera.test.repositories.ClienteRepository;
import com.supera.test.services.exceptions.notfound.ClienteNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Long id) throws ClienteNotFoundException {
		Cliente obj = repository.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente com id " + id + " não foi encontrado."));
		return obj;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}

	@Transactional
	public void delete(Long id) throws ClienteNotFoundException {
		if(repository.existsById(id) == false) {
			throw new ClienteNotFoundException("Cliente com id " + id + " não foi encontrado.");
		}
		repository.deleteById(id);

	}

	@Modifying
	@Transactional
	public Cliente update(Long id, Cliente obj) throws ClienteNotFoundException {
		if(repository.existsById(id) == false) {
			throw new ClienteNotFoundException("Cliente com id " + id + " não foi encontrado.");
		}
		Cliente entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setContato(obj.getContato());
		entity.setEndereco(obj.getEndereco());
		entity.getEndereco().setNumCasa(obj.getEndereco().getNumCasa());
		entity.getEndereco().setRua(obj.getEndereco().getRua());

	}
}
