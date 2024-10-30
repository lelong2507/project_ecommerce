package com.example.ProjectBE.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_detail")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    int idOrderDetail;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
    @Column(name = "quantity")
    int quantity;
    @Column(name = "price")
    double price;
    @Column(name = "status")
    boolean status;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
    @OneToOne(mappedBy = "orderDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Payment payment;
    @ManyToOne
    @JoinColumn(name = "voucher_id", nullable = false)
    Voucher voucher;
}
