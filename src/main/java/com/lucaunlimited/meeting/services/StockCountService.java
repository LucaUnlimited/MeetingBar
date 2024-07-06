package com.lucaunlimited.meeting.services;

import com.lucaunlimited.meeting.models.ProductEntity;
import com.lucaunlimited.meeting.models.ProductStockSummary;
import com.lucaunlimited.meeting.models.StockCount;
import com.lucaunlimited.meeting.repositorys.ProductRepository;
import com.lucaunlimited.meeting.repositorys.ProductStockSummaryRepository;
import com.lucaunlimited.meeting.repositorys.StockCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockCountService {

    @Autowired
    private StockCountRepository stockCountRepository;

    @Autowired
    private ProductStockSummaryRepository productStockSummaryRepository;


    @Autowired
    private ProductRepository productRepository;
    public void saveStockCount(String stockType) {
        List<ProductEntity> products = productRepository.findAll();
        LocalDate today = LocalDate.now();

        for (ProductEntity product : products) {
            ProductStockSummary summary = productStockSummaryRepository.findByDate(today).stream()
                    .filter(s -> s.getProductName().equals(product.getName()))
                    .findFirst()
                    .orElse(new ProductStockSummary());

            summary.setDate(today);
            summary.setProductName(product.getName());

            if ("START".equals(stockType)) {
                summary.setStockStart(product.getStock());
            } else if ("END".equals(stockType)) {
                summary.setStockEnd(product.getStock());
            }

            productStockSummaryRepository.save(summary);
        }
    }


    public List<StockCount> findByDate(LocalDate date) {
        return stockCountRepository.findAll().stream()
                .filter(stockCount -> stockCount.getCaptureDate().equals(date))
                .collect(Collectors.toList());
    }

}
