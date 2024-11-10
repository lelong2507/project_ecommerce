package com.example.ProjectBE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectBE.entities.OrderDetail;
import com.example.ProjectBE.service.OrderDetailService;

@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}
