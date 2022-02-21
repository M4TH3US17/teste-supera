package com.supera.test.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.supera.test.entities.Cliente;
import com.supera.test.entities.Endereco;
import com.supera.test.entities.Game;
import com.supera.test.repositories.CarrinhoRepository;
import com.supera.test.repositories.ClienteRepository;
import com.supera.test.repositories.GameRepository;
import com.supera.test.repositories.ItemCarrinhoRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;
	
	Double total = 0.0;
	Integer quantidade = 0;
	
	@Override
	public void run(String... args) throws Exception {
		Endereco objA = new Endereco("Av. samúma", 517);
		Cliente obj = new Cliente(null,"Matheus Dalvino", "(92) 00000-0000", objA);
		obj.setEndereco(objA);
		
		Endereco objB = new Endereco("Av. Pedro II", 312);
		Cliente obj2 = new Cliente(null,"Pedro Almeida", "(92) 22222-2222", objB);
		clienteRepository.saveAll(Arrays.asList(obj, obj2));
		
		Game g1 = new Game(null, "Clash Royale", "http://image/linkFicticio/royale", "Destrua a torre do seu inimigo!", 20.00);
		Game g2 = new Game(null, "Pou", "http://image/linkFicticio/pou", "Alimente seu pou.", 50.00);
		Game g3 = new Game(null, "Minecraft", "http://image/linkFicticio/minecraft", "Crie um mundo e explore-o.", 10.00);
		gameRepository.saveAll(Arrays.asList(g1, g2, g3));
		
		/*ItemCarrinho i1 = new ItemCarrinho(null, g1, g1.getPreco(), 1);
		ItemCarrinho i2 = new ItemCarrinho(null, g2, g2.getPreco(), 3);
		ItemCarrinho i3 = new ItemCarrinho(null, g3, g3.getPreco(), 2);
		//itemCarrinhoRepository.saveAll(Arrays.asList(i1, i2, i3));
		
		Carrinho o1 = new Carrinho(null, obj);
		Carrinho o2 = new Carrinho(null, obj2);
	
		o1.getItens().add(i1);
		o1.getItens().add(i2);
		o1.getItens().forEach(x -> {
			total += x.getPrecoTotal();
			quantidade += x.getQuantidade();
		});
		
		o2.getItens().add(i2);
		total = 0.0;
		quantidade = 0;
		o2.getItens().forEach(x -> {
			total += x.getPrecoTotal();
			quantidade += x.getQuantidade();
		});
		
		carrinhoRepository.saveAll(Arrays.asList(o1, o2));
		itemCarrinhoRepository.saveAll(Arrays.asList(i1, i2, i3));*/
	}
}
