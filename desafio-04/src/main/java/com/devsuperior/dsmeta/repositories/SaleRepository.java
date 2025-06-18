package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
	         + "FROM Sale obj WHERE obj.date BETWEEN :minDate AND :maxDate "
	         + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	    Page<SaleReportDTO> searchByDateAndSellerName(@Param("minDate") LocalDate minDate, 
	    		@Param("maxDate") LocalDate maxDate, @Param("name") String name, Pageable pageable);
	
	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj WHERE obj.date BETWEEN :minDate AND :maxDate GROUP BY obj.seller.name",
    countQuery = "SELECT COUNT(DISTINCT obj.seller.name) FROM Sale obj WHERE obj.date BETWEEN :minDate AND :maxDate")
	Page<SaleSummaryDTO> searchSummaryByDate(@Param("minDate") LocalDate minDate, 
						@Param("maxDate") LocalDate maxDate, Pageable pageable);
}