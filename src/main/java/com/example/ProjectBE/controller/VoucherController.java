package com.example.ProjectBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectBE.dto.request.VoucherDTO.VoucherCreationRequest;
import com.example.ProjectBE.dto.request.VoucherDTO.VoucherUpdateRequest;
import com.example.ProjectBE.entities.Voucher;
import com.example.ProjectBE.service.VoucherService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/vouchers")
public class VoucherController {

    @Autowiredß
    private VoucherService voucherService;

    @GetMapping("/get-all-voucher")
    List<Voucher> showAllVoucher() {
        return voucherService.getAllVouchers();
    }

    @GetMapping("/get-detail-voucher/{id}")
    ResponseEntity<?> showDetailVoucher(@PathVariable("id") String id) {
        try {
            int idVoucher = Integer.parseInt(id);
            Voucher voucher = voucherService.getVoucherById(idVoucher);
            System.out.println(voucher);
            if (voucher != null) {
                return new ResponseEntity<>(voucher, HttpStatus.OK);
            }
            return new ResponseEntity<>(voucher, HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid voucher ID format", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-voucher")
    ResponseEntity<?> addVoucher(@RequestBody VoucherCreationRequest req) {
        System.out.println(req.toString());
        try {
            voucherService.addVoucher(req);
            return new ResponseEntity<>(req, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add voucher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-voucher/{id}")
    ResponseEntity<?> deleteVoucher(@PathVariable("id") String id) {
        try {
            int idVoucher = Integer.parseInt(id);
            voucherService.deleteVoucher(idVoucher);
            return new ResponseEntity<>("Has been updated voucher with id:" + idVoucher, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid voucher ID format", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-voucher/{id}")
    ResponseEntity<?> updateVouhcer(@PathVariable String id, @RequestBody VoucherUpdateRequest request) {
        try {
            int idVoucher = Integer.parseInt(id);
            voucherService.updateVoucher(idVoucher, request);
            return new ResponseEntity<>("Has been deleted voucher with id:" + idVoucher, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid voucher ID format", HttpStatus.BAD_REQUEST);
        }
    }
}
