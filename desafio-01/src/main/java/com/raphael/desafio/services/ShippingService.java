package com.raphael.desafio.services;

import org.springframework.stereotype.Service;

import com.raphael.desafio.entities.Order;

@Service
public class ShippingService {
	
	// Calculo de Frete
	public double shipment(Order order) {
		
		if (order.getBasic() < 100) {
			return 20.0;
		} else if (order.getBasic() > 100 && order.getBasic() < 200) {
			return 12.0;
		} else {
			return 0.0;
		}
	}
	
}
