package com.kevinvolanski.cursomc.dto;

import java.io.Serializable;

import com.kevinvolanski.cursomc.domain.Cidade;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;


	private Integer id;
	private String nome;
	
	
	public CidadeDTO(Cidade cidade) {
		id = cidade.getId();
		nome = cidade.getNome();		
	}
	
	
}
