package com.kevinvolanski.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kevinvolanski.cursomc.domain.Cidade;
import com.kevinvolanski.cursomc.domain.Cliente;
import com.kevinvolanski.cursomc.domain.Endereco;
import com.kevinvolanski.cursomc.domain.enums.Perfil;
import com.kevinvolanski.cursomc.domain.enums.TipoCliente;
import com.kevinvolanski.cursomc.dto.ClienteDTO;
import com.kevinvolanski.cursomc.dto.ClienteNewDTO;
import com.kevinvolanski.cursomc.repositories.ClienteRepository;
import com.kevinvolanski.cursomc.repositories.EnderecoRepository;
import com.kevinvolanski.cursomc.security.UserSS;
import com.kevinvolanski.cursomc.services.exceptions.AuthorizationException;
import com.kevinvolanski.cursomc.services.exceptions.DataIntegrityException;
import com.kevinvolanski.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Cliente find(Integer id) {
		
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> cliente = clienteRepository.findById(id);		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
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
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}		
		return cliente;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDto) {		
		return new Cliente(clienteDto.getId(),clienteDto.getNome(),clienteDto.getEmail(),null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDto) {		
		Cliente cliente = new Cliente(null, clienteNewDto.getNome(), clienteNewDto.getEmail(), clienteNewDto.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDto.getTipoCliente()), bCryptPasswordEncoder.encode(clienteNewDto.getSenha()));
		Cidade cidade = new Cidade(clienteNewDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, clienteNewDto.getLogradouro(), clienteNewDto.getNumero(), clienteNewDto.getComplemento(), clienteNewDto.getBairro(), clienteNewDto.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		
		cliente.getTelefones().add(clienteNewDto.getTelefone1());
		if (clienteNewDto.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDto.getTelefone2());
			}
		if  (clienteNewDto.getTelefone3() != null) {
			cliente.getTelefones().add(clienteNewDto.getTelefone3());
		}
		
		return cliente;	
		
		}

	
	private void updateData(Cliente newClient, Cliente cliente) {
		newClient.setNome(cliente.getNome());
		newClient.setEmail(cliente.getEmail());
	}
	
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
