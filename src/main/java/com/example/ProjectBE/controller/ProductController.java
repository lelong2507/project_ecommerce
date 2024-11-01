package com.example.ProjectBE.controller;

import com.example.ProjectBE.dto.request.ProductDTO.ProductCreationRequest;
import com.example.ProjectBE.dto.request.ProductDTO.ProductUpdateRequest;
import com.example.ProjectBE.entities.Category;
import com.example.ProjectBE.entities.Product;
import com.example.ProjectBE.service.CategoryService;
import com.example.ProjectBE.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> showProduct() {
        return productService.showAllProduct();
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> showDetail(@PathVariable(name = "idProduct") int product_id) {
        Product product = productService.getProductById(product_id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductCreationRequest request) {
        System.out.println(request.toString());
        try {
            Category category = categoryService.findById(request.getIdCategory());
            if (category == null) {
                return new ResponseEntity<>("Category not found", HttpStatus.BAD_REQUEST);
            }

            Product product = new Product();
            product.setProductName(request.getProductName());
            product.setProductDesc(request.getProductDesc());
            product.setPrice(request.getPrice());
            product.setCategory(category);
            product.setImageUrl(request.getImgUrl());

            productService.createRequest(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save driver: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{product_id}")
    Product updateProduct(@RequestBody ProductUpdateRequest req, @PathVariable("product_id") int id) {
        return productService.updateProduct(id, req);
    }

    @DeleteMapping("/{product_id}")
    void deleteProduct(@PathVariable("product_id") int id) {
        productService.deleteProduct(id);
    }
}
