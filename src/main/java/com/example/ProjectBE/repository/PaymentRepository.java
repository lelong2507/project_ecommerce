package com.example.ProjectBE.repository;

import org.springframework.stereotype.Repository;

import com.example.ProjectBE.entities.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
