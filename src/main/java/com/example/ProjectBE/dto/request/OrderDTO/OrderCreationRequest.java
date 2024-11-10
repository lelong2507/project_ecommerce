package com.example.ProjectBE.dto.request.OrderDTO;

import java.util.Date;
import java.util.List;

import com.example.ProjectBE.dto.request.OrderDetailsDTO.OrderDetailCreationRequest;
import com.example.ProjectBE.entities.OrderDetail;

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
    List<OrderDetailCreationRequest> orderDetails;
}

// 0:category:{idCategory:1,nameCategory:'Nam'}idProduct:4
// imageUrl:"https://firebasestorage.googleapis.com/v0/b/projectshop-e2c5c.appspot.com/o/images%2F456325478_996203115535025_8440467258136516242_n.jpg?alt=media&token=81594e6c-20b1-4fd6-84a7-5a1acb40e9a1"orderDetails:[]price:15.9
// productDesc:"123"productName:"Áo sơ mi"quantity:1
// ratings:[][[Prototype]]:Object 1:category:{idCategory:2,nameCategory:'Nữ'}