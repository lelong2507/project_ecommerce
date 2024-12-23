package com.example.ProjectBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjectBE.entities.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

}
