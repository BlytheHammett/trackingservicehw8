package com.example.trackingservice.controller;

import com.example.trackingservice.model.Item;
import com.example.trackingservice.model.ItemDetails;
import com.example.trackingservice.model.Order;
import com.example.trackingservice.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trackings")
public class OrderController {

    private OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{orderId}/{itemId}")
    public Item getOrderStatus(@PathVariable int orderId, @PathVariable int itemId) {
        return repository.getOrderStatus(orderId, itemId);
    }

    @GetMapping
    public List<Order> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public int create(@Valid @RequestBody Order order) {
        return repository.create(order);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody ItemDetails itemDetails, @PathVariable int id) {
        repository.update(itemDetails, id);
    }

}
