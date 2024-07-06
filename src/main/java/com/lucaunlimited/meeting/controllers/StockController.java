package com.lucaunlimited.meeting.controllers;

import com.lucaunlimited.meeting.dto.ProductDTO;
import com.lucaunlimited.meeting.models.ProductEntity;
import com.lucaunlimited.meeting.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public String showStock(Model model) {
        List<ProductEntity> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        model.addAttribute("productList", products);
        model.addAttribute("newProduct", new ProductDTO());
        return "stock-list";
    }


}
