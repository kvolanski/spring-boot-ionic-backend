package com.kevinvolanski.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kevinvolanski.cursomc.domain.Categoria;
import com.kevinvolanski.cursomc.dto.CategoriaDTO;
import com.kevinvolanski.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {	
		Categoria categoria = categoriaService.find(id);		
		return ResponseEntity.ok(categoria);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDto){
		Categoria categoria = categoriaService.fromDTO(categoriaDto);
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaDto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDto, @PathVariable Integer id){
		Categoria categoria = categoriaService.fromDTO(categoriaDto);
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity <List<CategoriaDTO>> findAll() {	
		List <Categoria> listaCategorias = categoriaService.findAll();	
		List <CategoriaDTO> listaCategoriasDto = listaCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listaCategoriasDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity <Page<CategoriaDTO>> findPage(
			@RequestParam(name="page", defaultValue = "0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(name="direction", defaultValue = "ASC")String direction) {	
		Page <Categoria> listaCategorias = categoriaService.findPage(page, linesPerPage, orderBy, direction);	
		Page <CategoriaDTO> listaCategoriasDto = listaCategorias.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok(listaCategoriasDto);
	}
	
	
	
}
