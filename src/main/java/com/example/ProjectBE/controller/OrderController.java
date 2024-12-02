package com.example.ProjectBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectBE.dto.request.OrderDTO.OrderCreationRequest;
import com.example.ProjectBE.dto.request.OrderDTO.OrderUpdateRequest;
import com.example.ProjectBE.entities.Order;
import com.example.ProjectBE.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    ResponseEntity<Order> createOrder(@RequestBody OrderCreationRequest request) {
        Order createdOrder = orderService.saveOrder(request);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/update-order/{id}")
    ResponseEntity<?> updateOrder(@PathVariable(name = "id") String id, @RequestBody OrderUpdateRequest request) {
        System.out.println("'update order running .......'");
        try {
            int orderId = Integer.parseInt(id);
            Order updatedOrder = orderService.updateOrder(request, orderId);
            orderService.updateOrder(request, orderId);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid order ID format");
        }

    }

    @GetMapping("/get-all-order")
    List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/get-order-by-user/{id}")
    ResponseEntity<?> getOrderByUser(@PathVariable(name = "id") String id) {
        System.out.println("get order by user is running...");
        try {
            int userId = Integer.parseInt(id);
            List<Order> orders = orderService.getAllOrderByIdUser(userId);
            if (!orders.isEmpty()) {
                return ResponseEntity.ok(orders);
            }
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid user ID format");
        }
    }

    @GetMapping("/get-latest-order")
    ResponseEntity<Order> getLatestOrder() {

        Order latestOrder = orderService.getLatestOrder();
        if (latestOrder != null) {
            return ResponseEntity.ok(latestOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-order-by-id/{id}")
    ResponseEntity<?> getOrderById(@PathVariable(name = "id") String id) {
        System.out.println("'get order by id running .......'");
        try {
            int orderId = Integer.parseInt(id);
            Order order = orderService.getOrderById(orderId);
            if (order != null) {
                return ResponseEntity.ok(order);
            }
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid order ID format");
        }
    }

    @PostMapping("/access-bill/{id}")
    ResponseEntity<?> accessBill(@PathVariable(name = "id") String id) {
        System.out.println("access bill is running.....");
        try {
            int orderId = Integer.parseInt(id);
            orderService.accessBill(orderId);
            return ResponseEntity.ok("Bill accessed successfully");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid order ID format");
        }
    }

    @GetMapping("/get-latest-order-user/{id}")
    ResponseEntity<?> getLatestOrderByUser(@PathVariable(name = "id") String idUser) {
        try {
            int userId = Integer.parseInt(idUser);
            Order latestOrder = orderService.getOrderLatestByUserId(userId);
            if (latestOrder != null) {
                return ResponseEntity.ok(latestOrder);
            }
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid user ID format");
        }
    }
}
