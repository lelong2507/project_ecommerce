package com.example.ProjectBE.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "product")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    int idProduct;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_desc")
    String productDesc;

    @Column(name = "product_price")
    double price;

    @Column(name = "image_url")
    String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

}
