package com.example.ProjectBE.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "voucher")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voucher")
    int idVoucher;

    @Column(name = "voucher_name")
    String voucherName;

    @Column(name = "voucher_value")
    double voucherValue;

    @Column(name = "init_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date initDate;

    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OrderDetail> orderDetails;
}
