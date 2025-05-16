package com.raphael.desafio;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raphael.desafio.entities.Order;
import com.raphael.desafio.services.OrderService;
import com.raphael.desafio.services.ShippingService;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		// Inserir valores de entrada aqui!
		System.out.print("Code: ");
		int code = sc.nextInt();
		
		System.out.print("Basic: ");
		double basic = sc.nextDouble();
		
		System.out.print("Discount: ");
		double discount = sc.nextDouble();
		
		Order order = new Order(code, basic, discount);		
		ShippingService shippingService = new ShippingService();
		OrderService orderService = new OrderService(shippingService);
		
		System.out.println("Pedido código " + order.getCode());
		System.out.printf("Valor total: R$ %.2f%n", orderService.total(order));

		
		sc.close();
	}

}
