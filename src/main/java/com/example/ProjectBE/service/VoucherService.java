package com.example.ProjectBE.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectBE.dto.request.VoucherDTO.VoucherCreationRequest;
import com.example.ProjectBE.dto.request.VoucherDTO.VoucherUpdateRequest;
import com.example.ProjectBE.entities.Voucher;
import com.example.ProjectBE.repository.VocherRepository;

import jakarta.transaction.Transactional;

@Service
public class VoucherService {
    @Autowired
    private VocherRepository voucherRepository;

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    public Voucher addVoucher(VoucherCreationRequest request) {
        Voucher voucher = new Voucher();
        voucher.setVoucherName(request.getVoucherName());
        voucher.setVoucherValue(request.getVoucherValue());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String currentDateStr = simpleDateFormat.format(new Date());
            Date currentDate = simpleDateFormat.parse(currentDateStr);
            voucher.setInitDate(currentDate);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        voucherRepository.save(voucher);
        return voucher;
    }

    public void deleteVoucher(int id) {
        Voucher v = voucherRepository.getReferenceById(id);
        voucherRepository.delete(v);
    }

    @Transactional
    public Voucher updateVoucher(int id, VoucherUpdateRequest request) {
        Voucher v = voucherRepository.getReferenceById(id);
        if (v != null) {
            v.setVoucherName(request.getVoucherName());
            v.setVoucherValue(request.getVoucherValue());
            voucherRepository.save(v);
            return v;
        }
        System.out.println("Voucher not found");
        return null;
    }

    public Voucher getVoucherById(int id) {
        return voucherRepository.findById(id).orElse(null);
    }

}
