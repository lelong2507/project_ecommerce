package com.example.ProjectBE.dto.request.ProductDTO;

import com.example.ProjectBE.dto.request.CategoryDTO.CategoryRequest;
import com.example.ProjectBE.entities.Category;
import com.example.ProjectBE.entities.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductCreationRequest {
    String productName;
    String productDesc;
    double price;
    String imgUrl;
    int idCategory;

    public ProductCreationRequest(String productName, String productDesc, double price, String imgUrl, int idCategory) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.price = price;
        this.imgUrl = imgUrl;
        this.idCategory = idCategory;
    }

}
