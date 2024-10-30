package com.example.ProjectBE.service;

import com.example.ProjectBE.dto.request.ProductDTO.ProductCreationRequest;
import com.example.ProjectBE.dto.request.ProductDTO.ProductUpdateRequest;
import com.example.ProjectBE.entities.Category;
import com.example.ProjectBE.entities.Product;
import com.example.ProjectBE.repository.CategoryRepository;
import com.example.ProjectBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void createRequest(Product productRequest) {
        productRepository.save(productRequest);
    }

    public List<Product> showAllProduct() {
        return productRepository.findAll();
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public Product updateProduct(int productId, ProductUpdateRequest req) {
        Product product = getProductById(productId);

        product.setProductName(req.getProductName());
        product.setProductDesc(req.getProductDes());
        product.setPrice(req.getPrice());
        product.setImageUrl(req.getImgUrl());

        return productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.getReferenceById(id);
    }
}
