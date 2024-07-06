package com.lucaunlimited.meeting.repositorys;

import com.lucaunlimited.meeting.models.ProductStockSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductStockSummaryRepository extends JpaRepository<ProductStockSummary, Long> {
    List<ProductStockSummary> findByDate(LocalDate date);
}
