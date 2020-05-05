package com.kevinvolanski.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty (message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String Email;
	
}
