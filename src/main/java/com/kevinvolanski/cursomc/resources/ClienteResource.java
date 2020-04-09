package com.kevinvolanski.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kevinvolanski.cursomc.domain.Cliente;
import com.kevinvolanski.cursomc.dto.ClienteDTO;
import com.kevinvolanski.cursomc.dto.ClienteNewDTO;
import com.kevinvolanski.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {	
		Cliente cliente = clienteService.find(id);		
		return ResponseEntity.ok(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDto){
		Cliente cliente = clienteService.fromDTO(clienteNewDto);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO categoriaDto, @PathVariable Integer id){
		Cliente categoria = clienteService.fromDTO(categoriaDto);
		categoria.setId(id);
		categoria = clienteService.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity <List<ClienteDTO>> findAll() {	
		List <Cliente> listaClientes = clienteService.findAll();	
		List <ClienteDTO> listaClientesDto = listaClientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaClientesDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity <Page<ClienteDTO>> findPage(
			@RequestParam(name="page", defaultValue = "0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(name="direction", defaultValue = "ASC")String direction) {	
		Page <Cliente> listaClientes = clienteService.findPage(page, linesPerPage, orderBy, direction);	
		Page <ClienteDTO> listaClientesDto = listaClientes.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok(listaClientesDto);
	}
	
}