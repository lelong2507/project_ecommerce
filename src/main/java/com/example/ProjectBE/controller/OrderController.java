package com.example.ProjectBE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectBE.dto.request.OrderDTO.OrderCreationRequest;
import com.example.ProjectBE.entities.Order;
import com.example.ProjectBE.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreationRequest request) {

        Order createdOrder = orderService.saveOrder(request);
        return ResponseEntity.ok(createdOrder);
    }
}
