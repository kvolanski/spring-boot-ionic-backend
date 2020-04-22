package com.kevinvolanski.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinvolanski.cursomc.domain.Categoria;
import com.kevinvolanski.cursomc.domain.Cidade;
import com.kevinvolanski.cursomc.domain.Cliente;
import com.kevinvolanski.cursomc.domain.Endereco;
import com.kevinvolanski.cursomc.domain.Estado;
import com.kevinvolanski.cursomc.domain.ItemPedido;
import com.kevinvolanski.cursomc.domain.Pagamento;
import com.kevinvolanski.cursomc.domain.PagamentoComBoleto;
import com.kevinvolanski.cursomc.domain.PagamentoComCartao;
import com.kevinvolanski.cursomc.domain.Pedido;
import com.kevinvolanski.cursomc.domain.Produto;
import com.kevinvolanski.cursomc.domain.enums.EstadoPagamento;
import com.kevinvolanski.cursomc.domain.enums.TipoCliente;
import com.kevinvolanski.cursomc.repositories.CategoriaRepository;
import com.kevinvolanski.cursomc.repositories.CidadeRepository;
import com.kevinvolanski.cursomc.repositories.ClienteRepository;
import com.kevinvolanski.cursomc.repositories.EnderecoRepository;
import com.kevinvolanski.cursomc.repositories.EstadoRepository;
import com.kevinvolanski.cursomc.repositories.ItemPedidoRepository;
import com.kevinvolanski.cursomc.repositories.PagamentoRepository;
import com.kevinvolanski.cursomc.repositories.PedidoRepository;
import com.kevinvolanski.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
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
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instatiateTestDatabase() throws ParseException {
	
		
		Categoria categoria1 = new Categoria(null,"Informática");
		Categoria categoria2 = new Categoria(null,"Escritório");
		Categoria categoria3 = new Categoria(null,"Cama mesa e banho");
		Categoria categoria4 = new Categoria(null,"Eletronicos");
		Categoria categoria5 = new Categoria(null,"Jardinagem");
		Categoria categoria6 = new Categoria(null,"Decoração");
		Categoria categoria7 = new Categoria(null,"Perfumaria");
		//Categoria categoria8 = new Categoria(null,"Teste");
		
		Produto produto1 = new Produto(null,"Computador",2000.00);
		Produto produto2 = new Produto(null,"Impressora",800.00);
		Produto produto3 = new Produto(null,"Mouse",80.00);
		Produto produto4 = new Produto(null,"Mesa de escritorio",300.00);
		Produto produto5 = new Produto(null,"Toalha",50.00);
		Produto produto6 = new Produto(null,"Colcha",200.00);
		Produto produto7 = new Produto(null,"TV true color",1200.00);
		Produto produto8 = new Produto(null,"Roçadeira",800.00);
		Produto produto9 = new Produto(null,"Abajur",100.00);
		Produto produto10 = new Produto(null,"Pendente",180.00);
		Produto produto11 = new Produto(null,"Shampoo",90.00);
		
		
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
		categoria2.getProdutos().addAll(Arrays.asList(produto2,produto4));
		categoria3.getProdutos().addAll(Arrays.asList(produto5,produto6));
		categoria4.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3,produto4));
		categoria5.getProdutos().addAll(Arrays.asList(produto8));
		categoria6.getProdutos().addAll(Arrays.asList(produto9,produto10));
		categoria7.getProdutos().addAll(Arrays.asList(produto11));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1,categoria4));
		produto2.getCategorias().addAll(Arrays.asList(categoria1,categoria2,categoria4));
		produto3.getCategorias().addAll(Arrays.asList(categoria1,categoria4));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria3));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria6));
		produto11.getCategorias().addAll(Arrays.asList(categoria7));
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2,cidade3));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pedido1 = new Pedido(null, simpleDateFormat.parse("30/09/2017 16:24"), null, cliente1,endereco );
		Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("10/10/2017 17:00"), null, cliente1, endereco2);
		
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,simpleDateFormat.parse("20/10/2017 00:00"),null );
		pedido2.setPagamento(pagamento2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1,pedido2));
		
		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00 );
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));
		
		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2,categoria3,categoria3,categoria4,categoria5,categoria6,categoria7));
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4,produto5,produto6,produto7,produto8,produto9,produto10,produto11));
		estadoRepository.saveAll(Arrays.asList(estado1,estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2,cidade3));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco,endereco2));
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2));
		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
		
	}
	
}