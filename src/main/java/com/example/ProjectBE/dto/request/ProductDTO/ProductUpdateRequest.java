package com.example.ProjectBE.dto.request.ProductDTO;

import com.example.ProjectBE.entities.Category;
import com.example.ProjectBE.entities.Rating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ProductUpdateRequest {
    String productName;
    String productDes;
    double price;
    String imgUrl;
    Category category;
    Rating rating;
}
