package com.example.ProjectBE.dto.request.OrderDTO;

import java.util.List;

import com.example.ProjectBE.dto.request.OrderDetailsDTO.OrderDetailCreationRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateRequest {
    int idPayment;
    int idVoucher;
    double price;
    List<OrderDetailCreationRequest> orderDetails;
}
