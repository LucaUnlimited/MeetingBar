package com.lucaunlimited.meeting.controllers;

import com.lucaunlimited.meeting.dto.ProductDTO;
import com.lucaunlimited.meeting.models.ProductEntity;
import com.lucaunlimited.meeting.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public String addProduct(@ModelAttribute ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStock(productDTO.getStock());
        productRepository.save(productEntity);
        return "redirect:/stock";
    }

    @PostMapping("/edit")
    public String editProduct(@RequestParam Long id, @RequestParam Integer stock) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Product not found"));
        productEntity.setStock(stock);
        productRepository.save(productEntity);
        return "redirect:/stock";
    }


}
