package com.example.ProjectBE.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjectBE.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
