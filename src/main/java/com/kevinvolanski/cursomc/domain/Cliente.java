package com.kevinvolanski.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kevinvolanski.cursomc.domain.enums.TipoCliente;

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
public class Cliente implements Serializable {


	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Integer id;
	
	@Getter @Setter private String nome;
	@Getter @Setter private String email;
	@Getter @Setter private String cpfOuCnpj;
	
	private Integer tipoCliente;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	@Getter @Setter private List<Endereco> enderecos = new ArrayList<>();
	
	
	//Definido como um conjunto de strings, não repetindo valores.
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	@Getter @Setter private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy = "cliente")
	@Getter @Setter private List<Pedido> pedidos = new ArrayList<>();

	
	
	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente.getCod();
	}


	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(tipoCliente);
	}


	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCod();;
	}
	
	
	
	
}


