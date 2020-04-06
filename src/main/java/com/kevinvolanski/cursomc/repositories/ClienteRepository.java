package com.kevinvolanski.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinvolanski.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
