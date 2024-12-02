package com.example.ProjectBE.dto.request.OrderDetailsDTO;

import org.antlr.v4.runtime.misc.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@ToString
public class OrderDetailCreationRequest {
    int idProduct;
    int quantity;
}
