package com.kevinvolanski.cursomc.domain;

import javax.persistence.Entity;

import com.kevinvolanski.cursomc.domain.enums.EstadoPagamento;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,
			Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}


}

//@Entity
//@Data
//public class PagamentoComCartao extends Pagamento {
//
//	private static final long serialVersionUID = 1L;
//	
//	@Getter @Setter private Integer NumeroParcelas;
//
//	public PagamentoComCartao(Integer numeroParcelas, Integer id, Pedido pedido ) {
//		super();
//		NumeroParcelas = numeroParcelas;
//	}
//
//	public PagamentoComCartao(Object object, EstadoPagamento quitado, Pedido pedido1, int i) {
//		// TODO Auto-generated constructor stub
//	}
//
//	
//	
//	
//}
