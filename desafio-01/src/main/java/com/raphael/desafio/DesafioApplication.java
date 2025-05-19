package com.raphael.desafio;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raphael.desafio.entities.Order;
import com.raphael.desafio.services.OrderService;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	
	@Autowired
	private OrderService orderService;
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Locale.setDefault(Locale.US);
		
		Order order1 = new Order(1034, 150.0, 20.0);		
		System.out.println("Pedido código " + order1.getCode());
		System.out.printf("Valor total: R$ %.2f%n", orderService.total(order1));

		
		Order order2 = new Order(2282, 800.0, 10.0);		
		System.out.println("Pedido código " + order2.getCode());
		System.out.printf("Valor total: R$ %.2f%n", orderService.total(order2));
		
		
		Order order3 = new Order(1309, 95.90, 0.0);		
		System.out.println("Pedido código " + order3.getCode());
		System.out.printf("Valor total: R$ %.2f%n", orderService.total(order3));


	}

}
