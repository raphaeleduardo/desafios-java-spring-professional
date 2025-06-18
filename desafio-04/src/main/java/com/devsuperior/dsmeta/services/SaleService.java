package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getSaleReport(String minDateStr, String maxDateStr, String sellerName,
			Pageable pageable) {

		LocalDate today = LocalDate.now();

		// Chamada ao método que trata as datas
		LocalDate minDate = parseMinDate(minDateStr, today);
		LocalDate maxDate = parseMaxDate(maxDateStr, today);

		String name = (sellerName == null) ? "" : sellerName;

		return repository.searchByDateAndSellerName(minDate, maxDate, name, pageable);
	}
	
	public Page<SaleSummaryDTO> getSalesSummary(String minDateStr, String maxDateStr, Pageable pageable) {
        LocalDate today = LocalDate.now();

        LocalDate minDate = parseMinDate(minDateStr, today);
        LocalDate maxDate = parseMaxDate(maxDateStr, today);

        return repository.searchSummaryByDate(minDate, maxDate, pageable);
    }

	// Método privado para tratar a data mínima
	private LocalDate parseMinDate(String minDateStr, LocalDate today) {
		if (minDateStr == null || minDateStr.isBlank()) {
			return today.minusYears(1).withDayOfMonth(1); // Começo do mês há 1 ano atrás
		}
		return LocalDate.parse(minDateStr);
	}

	// Método privado para tratar a data máxima
	private LocalDate parseMaxDate(String maxDateStr, LocalDate today) {
		if (maxDateStr == null || maxDateStr.isBlank()) {
			return today; // Hoje
		}
		return LocalDate.parse(maxDateStr);
	}

}
