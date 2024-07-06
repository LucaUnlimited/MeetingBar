package com.lucaunlimited.meeting.repositorys;

import com.lucaunlimited.meeting.models.StockCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface StockCountRepository extends JpaRepository<StockCount, Long> {
    Optional<StockCount> findByCaptureDateAndStockType(LocalDate captureDate, String stockType);

}
