package com.lucaunlimited.meeting.services;

import com.lucaunlimited.meeting.models.HabEntity;
import com.lucaunlimited.meeting.models.OrderEntity;
import com.lucaunlimited.meeting.models.ProductEntity;
import com.lucaunlimited.meeting.models.StockCount;
import com.lucaunlimited.meeting.repositorys.HabRepository;
import com.lucaunlimited.meeting.repositorys.OrderRepository;
import com.lucaunlimited.meeting.repositorys.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HabRepository habRepository;


    @Transactional
    public void createOrder(String guestName, Long productId, Long habId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() <= 0) {
            throw new RuntimeException("Product out of stock");
        }

        HabEntity room = habRepository.findById(habId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        OrderEntity order = new OrderEntity();
        order.setGuestName(guestName);
        order.setProduct(product);
        order.setHab(room);
        order.setOrderDate(LocalDate.now());

        product.setStock(product.getStock() - 1);
        productRepository.save(product);

        orderRepository.save(order);
    }


    public List<OrderEntity> findByDate(LocalDate date) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getOrderDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<OrderEntity> getOrdersByHabId(Long habId) {
        return orderRepository.findByHabIdOrderByOrderDateDesc(habId);
    }


}
