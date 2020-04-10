package com.kevinvolanski.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.kevinvolanski.cursomc.domain.Cliente;
import com.kevinvolanski.cursomc.services.validation.ClienteUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@Data
@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	@NotEmpty (message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty (message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}

}
