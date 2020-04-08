package com.kevinvolanski.cursomc.dto;

import java.io.Serializable;

import com.kevinvolanski.cursomc.domain.Categoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoriaDTO  implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}
	
}
