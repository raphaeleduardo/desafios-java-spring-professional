package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/report")
    public ResponseEntity<Page<SaleReportDTO>> getReport(
            @RequestParam(name = "minDate", required = false) String minDate,
            @RequestParam(name = "maxDate", required = false) String maxDate,
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable) {

        Page<SaleReportDTO> result = service.getSaleReport(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(result);
    }

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSummaryDTO>> getSalesSummary(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate, Pageable pageable) {

		Page<SaleSummaryDTO> result = service.getSalesSummary(minDate, maxDate, pageable);
		return ResponseEntity.ok(result);
	}
}
