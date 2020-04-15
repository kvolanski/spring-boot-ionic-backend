package com.kevinvolanski.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevinvolanski.cursomc.domain.Produto;
import com.kevinvolanski.cursomc.dto.ProdutoDTO;
import com.kevinvolanski.cursomc.resources.utils.URL;
import com.kevinvolanski.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService pedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {	
		Produto pedido = pedidoService.find(id);		
		return ResponseEntity.ok(pedido);
	}
	
	@RequestMapping( method=RequestMethod.GET)
	public ResponseEntity <Page<ProdutoDTO>> findPage(
			@RequestParam(name="nome", defaultValue = "") String nome, 
			@RequestParam(name="categorias", defaultValue = "") String categorias,
			@RequestParam(name="page", defaultValue = "0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(name="direction", defaultValue = "ASC")String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page <Produto> lista = pedidoService.search(nomeDecoded, ids,page, linesPerPage, orderBy, direction);	
		Page <ProdutoDTO> listaProdutoDto = lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok(listaProdutoDto);
	}
}
