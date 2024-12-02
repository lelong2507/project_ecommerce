package com.example.ProjectBE.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.ProjectBE.dto.request.OrderDTO.OrderUpdateRequest;
import com.example.ProjectBE.entities.Order;
import com.example.ProjectBE.entities.Payment;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.repository.OrderRepository;
import com.example.ProjectBE.repository.PaymentRepository;
import com.example.ProjectBE.repository.UserRepository;
import com.example.ProjectBE.vnpay.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/vnpay")
public class VNPaymentController {

    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submitOrder")
    public ResponseEntity<Map<String, String>> submitOrder(@RequestBody OrderUpdateRequest orderRequest,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() +
                (request.getServerPort() != 80 && request.getServerPort() != 443 ? ":" + request.getServerPort() : "");
        String vnpayUrl = vnPayService.createOrder(orderRequest.getPrice(), "Order Payment", baseUrl);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", vnpayUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/vnpay-payment")
    public RedirectView paymentResult(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        String status = "OrderFail";

        if (paymentStatus == 1) {
            status = "OrderSuccess";
            try {
                String[] arr = orderInfo.split(";");
                String username = arr[0];
                Integer orderId = Integer.parseInt(arr[1]);
                User user = userRepository.findByUserName(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException("Order not found"));
                Payment payment = paymentRepository.findById(Integer.parseInt(transactionId))
                        .orElseThrow(() -> new RuntimeException("Payment not found"));
                order.setPayment(payment);
                order.setStatus(true);
                orderRepository.save(order);
            } catch (Exception e) {
                status = "OrderFail";
            }
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/payment-result?status=" + status);

        return redirectView;
    }
}
