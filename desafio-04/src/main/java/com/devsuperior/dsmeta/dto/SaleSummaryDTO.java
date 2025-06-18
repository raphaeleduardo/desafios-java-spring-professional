package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SaleSummaryDTO {

	private String sellerName;
	private Double totalAmount;

	public SaleSummaryDTO(String sellerName, Double totalAmount) {
		this.sellerName = sellerName;
		this.totalAmount = totalAmount;
	}

	public SaleSummaryDTO(Sale sale, Seller seller) {
		totalAmount = sale.getAmount();
		sellerName = seller.getName();
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

}
