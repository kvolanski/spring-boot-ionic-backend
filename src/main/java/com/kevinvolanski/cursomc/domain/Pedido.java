package com.kevinvolanski.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Integer id;
	
	@Getter @Setter private Date instante;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	@Getter @Setter private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	@Getter @Setter private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_entrega_id")
	@Getter @Setter private Endereco enderecoEntrega;
	
	

}
