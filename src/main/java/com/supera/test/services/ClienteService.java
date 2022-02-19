package com.supera.test.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.supera.test.entities.Cliente;
import com.supera.test.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NullPointerException());
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		return repository.save(obj);
	}

	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);

	}

	@Modifying
	@Transactional
	public Cliente update(Long id, Cliente obj) {
		Cliente entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setContato(obj.getContato());
		entity.setEndereco(obj.getEndereco());
		entity.getEndereco().setRua(obj.getEndereco().getRua());
		entity.getEndereco().setNumCasa(obj.getEndereco().getNumCasa());

	}
}
