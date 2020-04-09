package com.kevinvolanski.cursomc.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor @NoArgsConstructor
@Data
public class ClienteNewDTO implements Serializable {


	private static final long serialVersionUID = 1L; 

	private String nome;
	private String email;
	private String cpfOuCnpj;
	
	private Integer tipoCliente;	
	
	private String logradouro;
	private String numero;	
	private String complemento;
	private String bairro;
	private String cep;
	
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
}
