package com.example.ProjectBE.repository;

import com.example.ProjectBE.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
