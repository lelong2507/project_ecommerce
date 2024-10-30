package com.example.ProjectBE.service;

import com.example.ProjectBE.dto.request.CategoryDTO.CategoryCreationRequest;
import com.example.ProjectBE.dto.request.ProductDTO.ProductCreationRequest;
import com.example.ProjectBE.entities.Category;
import com.example.ProjectBE.entities.Product;
import com.example.ProjectBE.repository.CategoryRepository;
import com.example.ProjectBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public void createRequest(Product request) {
        productRepository.save(request);
    }

    public List<Category> showListCate() {
        return categoryRepository.findAll();
    }

    public Category updateCate(int id, CategoryCreationRequest request) {
        Category category = findById(id);
        if (category != null) {
            category.setNameCategory(request.getNameCategory());
            return category;
        }
        return null;
    }

    public Category findById(int id) {
        return categoryRepository.getReferenceById(id);
    }
}
