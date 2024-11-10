package com.example.ProjectBE.service;

import com.example.ProjectBE.dto.request.ProductDTO.ProductDetailsRequest;
import com.example.ProjectBE.dto.request.ProductDTO.ProductUpdateRequest;
import com.example.ProjectBE.entities.Category;
import com.example.ProjectBE.entities.Product;
import com.example.ProjectBE.repository.CategoryRepository;
import com.example.ProjectBE.repository.ProductRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(Product product, int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public List<Product> showAllProduct() {
        return productRepository.findAll();
    }

    public void deleteProduct(int id) {
        Product product = productRepository.getReferenceById(id);
        productRepository.delete(product);
    }

    @Transactional
    public Product updateProduct(int productId, int idCategory, ProductUpdateRequest req) {
        Product product = productRepository.getReferenceById(productId);
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        product.setProductName(req.getProductName());
        product.setProductDesc(req.getProductDes());
        product.setPrice(req.getPrice());
        product.setImageUrl(req.getImgUrl());
        product.setCategory(category);
        return productRepository.save(product);
    }

    public ProductDetailsRequest getProductById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            ProductDetailsRequest productDetailsRequest = new ProductDetailsRequest();
            productDetailsRequest.setProductName(product.getProductName());
            productDetailsRequest.setProductDesc(product.getProductDesc());
            productDetailsRequest.setPrice(product.getPrice());
            productDetailsRequest.setImageUrl(product.getImageUrl());
            productDetailsRequest.setCategory(product.getCategory());

            return productDetailsRequest;
        }

        return null;
    }

    public Product getProductByIdProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }
}
