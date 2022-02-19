package com.supera.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supera.test.entities.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

}
