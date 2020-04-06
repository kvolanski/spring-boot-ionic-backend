package com.kevinvolanski.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kevinvolanski.cursomc.domain.Categoria;
import com.kevinvolanski.cursomc.domain.Cidade;
import com.kevinvolanski.cursomc.domain.Cliente;
import com.kevinvolanski.cursomc.domain.Endereco;
import com.kevinvolanski.cursomc.domain.Estado;
import com.kevinvolanski.cursomc.domain.Produto;
import com.kevinvolanski.cursomc.domain.enums.TipoCliente;
import com.kevinvolanski.cursomc.repositories.CategoriaRepository;
import com.kevinvolanski.cursomc.repositories.CidadeRepository;
import com.kevinvolanski.cursomc.repositories.ClienteRepository;
import com.kevinvolanski.cursomc.repositories.EnderecoRepository;
import com.kevinvolanski.cursomc.repositories.EstadoRepository;
import com.kevinvolanski.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null,"Informática");
		Categoria categoria2 = new Categoria(null,"Escritório");
		Categoria categoria3 = new Categoria(null,"Mesa e banho");
		
		Produto produto1 = new Produto(null,"Computador",2000.00);
		Produto produto2 = new Produto(null,"Impressora",800.00);
		Produto produto3 = new Produto(null,"Mouse",80.00);
		
		Estado estado1 = new Estado(null,"Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlandia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		Cliente cliente1 = new Cliente(null,"Maria Silva","maria@gmail.com","15473780008",TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("99785454", "987451236"));
		
		Endereco endereco = new Endereco (null,"Rua Flores","39","Casa","Jardim","82840650",cliente1,cidade1);
		Endereco endereco2 = new Endereco(null,"Rua Margaria","80","Apartamento","Apto01","82815690",cliente1,cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco,endereco2));
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
	
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2,categoria3));
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco,endereco2));
		
		
	}

}
