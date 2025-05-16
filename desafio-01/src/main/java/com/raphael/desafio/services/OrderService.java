package com.raphael.desafio.services;

import org.springframework.stereotype.Service;

import com.raphael.desafio.entities.Order;

@Service
public class OrderService {

	private ShippingService shippingService;
	
	public OrderService() {
	}

	public OrderService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	public double total(Order order) {
		double discount = 1 - (order.getDiscount() / 100);
		return (order.getBasic() * discount) + shippingService.shipment(order);
	}

}
