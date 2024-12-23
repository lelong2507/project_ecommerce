package com.example.ProjectBE.dto.request.OrderDTO;

import java.util.Date;
import java.util.List;

import com.example.ProjectBE.dto.request.OrderDetailsDTO.OrderDetailCreationRequest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
public class OrderCreationRequest {
    Date dateOrder;
    double vat;
    int idUser;
    double price;
    List<OrderDetailCreationRequest> orderDetails;
}

