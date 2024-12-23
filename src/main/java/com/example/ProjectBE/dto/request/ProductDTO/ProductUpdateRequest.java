package com.example.ProjectBE.dto.request.ProductDTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class ProductUpdateRequest {
    String productName;
    String productDes;
    double price;
    String imgUrl;
    int idCategory;
}
