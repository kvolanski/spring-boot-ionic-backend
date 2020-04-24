package com.kevinvolanski.cursomc.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CredenciaisDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;

}
