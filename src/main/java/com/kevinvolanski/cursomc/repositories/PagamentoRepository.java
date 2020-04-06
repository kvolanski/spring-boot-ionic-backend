package com.kevinvolanski.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinvolanski.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
