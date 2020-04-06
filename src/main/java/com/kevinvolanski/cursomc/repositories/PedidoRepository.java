package com.kevinvolanski.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinvolanski.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
