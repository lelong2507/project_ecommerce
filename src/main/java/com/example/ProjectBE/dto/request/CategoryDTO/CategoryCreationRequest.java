package com.example.ProjectBE.dto.request.CategoryDTO;

import com.example.ProjectBE.entities.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CategoryCreationRequest {
    String nameCategory;
}
