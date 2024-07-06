package com.lucaunlimited.meeting.controllers;

import com.lucaunlimited.meeting.models.OrderEntity;
import com.lucaunlimited.meeting.repositorys.HabRepository;
import com.lucaunlimited.meeting.repositorys.OrderRepository;
import com.lucaunlimited.meeting.repositorys.ProductRepository;
import com.lucaunlimited.meeting.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HabRepository habRepository;

    @Autowired
    private OrderRepository orderRepository;



    @GetMapping()
    public String showOrderForm(Model model) {
        LocalDate today = LocalDate.now();

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("rooms", habRepository.findAll());
        model.addAttribute("ordersToday", orderService.findByDate(today));

        return "order-form";
    }


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestParam String guestName,
                                              @RequestParam Long productId,
                                              @RequestParam Long roomId) {
        try {
            orderService.createOrder(guestName, productId, roomId);
            return ResponseEntity.ok("Order created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/room/{habId}")
    public String getOrdersByHabId(@PathVariable Long habId, Model model) {
        List<OrderEntity> orders = orderService.getOrdersByHabId(habId);
        model.addAttribute("orders", orders);
        return "orders-by-room";
    }

}
