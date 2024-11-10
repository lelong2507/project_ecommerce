package com.example.ProjectBE.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProjectBE.entities.Order;
import com.example.ProjectBE.entities.OrderDetail;
import com.example.ProjectBE.entities.Product;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.repository.OrderDetailRepository;
import com.example.ProjectBE.repository.OrderRepository;
import com.example.ProjectBE.repository.ProductRepository;
import com.example.ProjectBE.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order saveOrder(com.example.ProjectBE.dto.request.OrderDTO.OrderCreationRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        orderRequest.setDateOrder(new Date());
        Order order = new Order();
        order.setDateOrder(orderRequest.getDateOrder());
        order.setVat(orderRequest.getVat());
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);

        List<OrderDetail> orderDetails = orderRequest.getOrderDetails().stream().map(detailRequest -> {
            OrderDetail orderDetail = new OrderDetail();

            Product product = productRepository.findById(detailRequest.getIdProduct())
                    .orElseThrow(() -> {
                        System.out.println("Product not found for ID: " + detailRequest.getIdProduct());
                        return new RuntimeException("Product not found");
                    });
            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailRequest.getQuantity());
            orderDetail.setPrice(detailRequest.getPrice());
            orderDetail.setStatus(false);
            orderDetail.setOrder(savedOrder);
            System.out.println(orderDetail);
            return orderDetail;
        }).collect(Collectors.toList());
        orderDetailRepository.saveAll(orderDetails);

        savedOrder.setOrderDetails(orderDetails);

        return savedOrder;
    }
}
