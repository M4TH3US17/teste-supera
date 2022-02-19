package com.supera.test.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.supera.test.entities.Carrinho;
import com.supera.test.entities.Cliente;
import com.supera.test.entities.Endereco;
import com.supera.test.entities.Game;
import com.supera.test.repositories.CarrinhoRepository;
import com.supera.test.repositories.ClienteRepository;
import com.supera.test.repositories.GameRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Endereco objA = new Endereco("Av. samúma", 517);
		Cliente obj = new Cliente(null,"Matheus Dalvino", "(92) 00000-0000", objA);
		obj.setEndereco(objA);
		
		Endereco objB = new Endereco("Av. Pedro II", 312);
		Cliente obj2 = new Cliente(null,"Pedro Almeida", "(92) 22222-2222", objB);
		clienteRepository.saveAll(Arrays.asList(obj, obj2));
		
		Game g1 = new Game(null, "Clash Royale", "http://image/linkFicticio/royale", "Destrua a torre do seu inimigo!", 20.00);
		Game g2 = new Game(null, "Pou", "http://image/linkFicticio/pou", "Alimente seu pou.", 5.00);
		Game g3 = new Game(null, "Minecraft", "http://image/linkFicticio/minecraft", "Crie um mundo e explore-o.", 10.00);
		gameRepository.saveAll(Arrays.asList(g1, g2, g3));
		
		Carrinho o1 = new Carrinho(null, obj);
		o1.addItem(g1);
		o1.addItem(g2);
		
		Carrinho o2 = new Carrinho(null, obj2);
		o2.addItem(g3);
		carrinhoRepository.saveAll(Arrays.asList(o1, o2));					
	}
}
