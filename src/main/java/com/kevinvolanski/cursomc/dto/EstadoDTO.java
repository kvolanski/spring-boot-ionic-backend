package com.kevinvolanski.cursomc.dto;

import java.io.Serializable;

import com.kevinvolanski.cursomc.domain.Estado;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstadoDTO  implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public EstadoDTO(Estado estado) {
		id = estado.getId();
		nome = estado.getNome();
	}
	
		
	
}
