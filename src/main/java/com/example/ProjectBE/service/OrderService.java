package com.example.ProjectBE.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectBE.dto.request.OrderDTO.OrderCreationRequest;
import com.example.ProjectBE.dto.request.OrderDTO.OrderUpdateRequest;
import com.example.ProjectBE.entities.Order;
import com.example.ProjectBE.entities.OrderDetail;
import com.example.ProjectBE.entities.Payment;
import com.example.ProjectBE.entities.Product;
import com.example.ProjectBE.entities.User;
import com.example.ProjectBE.entities.Voucher;
import com.example.ProjectBE.repository.OrderDetailRepository;
import com.example.ProjectBE.repository.OrderRepository;
import com.example.ProjectBE.repository.PaymentRepository;
import com.example.ProjectBE.repository.ProductRepository;
import com.example.ProjectBE.repository.UserRepository;
import com.example.ProjectBE.repository.VoucherRepository;

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

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Transactional
    public Order saveOrder(OrderCreationRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getIdUser())
                .orElseThrow(() -> new RuntimeException("User not found"));

        orderRequest.setDateOrder(new Date());
        Order order = new Order();
        order.setDateOrder(orderRequest.getDateOrder());
        order.setVat(orderRequest.getVat());
        order.setUser(user);
        order.setPrice(orderRequest.getPrice());

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
            orderDetail.setOrder(savedOrder);
            System.out.println(orderDetail);
            return orderDetail;
        }).collect(Collectors.toList());
        orderDetailRepository.saveAll(orderDetails);

        savedOrder.setOrderDetails(orderDetails);

        return savedOrder;
    }

    @Transactional
    public Order updateOrder(OrderUpdateRequest request, int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        Payment payment = paymentRepository.findById(request.getIdPayment())
                .orElseThrow(() -> new RuntimeException("Payment not found."));
        Voucher voucher = voucherRepository.findById(request.getIdVoucher())
                .orElseThrow(() -> new RuntimeException("Voucher not found."));
        order.setPayment(payment);
        order.setVoucher(voucher);

        List<OrderDetail> orderDetails = request.getOrderDetails().stream().map(detailRequest -> {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(detailRequest.getIdProduct())
                    .orElseThrow(
                            () -> new RuntimeException("Product not found for ID: " + detailRequest.getIdProduct()));

            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailRequest.getQuantity());
            orderDetail.setOrder(order);

            return orderDetail;
        }).collect(Collectors.toList());

        orderDetailRepository.deleteAll(order.getOrderDetails());
        orderDetailRepository.saveAll(orderDetails);

        order.setOrderDetails(orderDetails);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));
    }

    public Order getLatestOrder() {
        return orderRepository.findTopByOrderByIdOrderDesc();
    }

    @Transactional
    public void accessBill(int idOrder) {
        Order order = orderRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("Order not found."));
        order.setStatus(true);
    }

    public Order getOrderLatestByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return orderRepository.findTopByUserOrderByIdOrderDesc(user);
    }

    public List<Order> getAllOrderByIdUser(int idUser) {
        List<Order> allOrder = orderRepository.findOrderByUser_IdUser(idUser);
        return allOrder;
    }
}
