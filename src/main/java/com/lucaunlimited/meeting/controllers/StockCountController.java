package com.lucaunlimited.meeting.controllers;

import com.lucaunlimited.meeting.models.ProductEntity;
import com.lucaunlimited.meeting.models.ProductStockSummary;
import com.lucaunlimited.meeting.models.StockCount;
import com.lucaunlimited.meeting.repositorys.ProductStockSummaryRepository;
import com.lucaunlimited.meeting.services.StockCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/stock-count")
public class StockCountController {


    @Autowired
    private StockCountService stockCountService;

    @Autowired
    private ProductStockSummaryRepository productStockSummaryRepository;



    @PostMapping("/save-start")
    public ResponseEntity<String> saveStartStock() {
        stockCountService.saveStockCount("START");
        return ResponseEntity.ok("Stock inicial guardado o actualizado exitosamente.");
    }

    @PostMapping("/save-end")
    public ResponseEntity<String> saveEndStock() {
        stockCountService.saveStockCount("END");
        return ResponseEntity.ok("Stock final guardado o actualizado exitosamente.");
    }

    @GetMapping("/summary")
    public String getStockSummary(@RequestParam("date") String date, Model model) {
        LocalDate localDate = LocalDate.parse(date);
        List<ProductStockSummary> stockSummary = productStockSummaryRepository.findByDate(localDate);
        model.addAttribute("stockSummary", stockSummary);
        return "stock-summary";
    }

}
