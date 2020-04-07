package com.kevinvolanski.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class ItemPedidoPK implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	@EqualsAndHashCode.Include
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	@EqualsAndHashCode.Include
	private Produto produto;
	
	
}
