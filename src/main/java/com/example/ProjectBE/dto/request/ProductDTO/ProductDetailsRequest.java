package com.example.ProjectBE.dto.request.ProductDTO;


import com.example.ProjectBE.entities.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailsRequest {
    String productName;
    String productDesc;
    double price;
    String imageUrl;
    Category category;
}
