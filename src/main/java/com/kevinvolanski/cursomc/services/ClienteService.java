package com.kevinvolanski.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kevinvolanski.cursomc.domain.Cliente;
import com.kevinvolanski.cursomc.dto.ClienteDTO;
import com.kevinvolanski.cursomc.repositories.ClienteRepository;
import com.kevinvolanski.cursomc.services.exceptions.DataIntegrityException;
import com.kevinvolanski.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newClient = find(cliente.getId());
		updateData(newClient, cliente);
		return clienteRepository.save(newClient);
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		try {
			clienteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDto) {		
		return new Cliente(clienteDto.getId(),clienteDto.getNome(),clienteDto.getEmail(),null, null);
	}
	
	private void updateData(Cliente newClient, Cliente cliente) {
		newClient.setNome(cliente.getNome());
		newClient.setEmail(cliente.getEmail());
	}
	
}
