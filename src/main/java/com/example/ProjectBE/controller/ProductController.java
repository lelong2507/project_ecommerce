package com.example.ProjectBE.controller;

import com.example.ProjectBE.dto.request.ProductDTO.ProductCreationRequest;
import com.example.ProjectBE.dto.request.ProductDTO.ProductDetailsRequest;
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

    @GetMapping("/product-detail/{idProduct}")
    ResponseEntity<?> showDetail(@PathVariable(name = "idProduct") String product_id) {
        System.out.println("details product .............");
        try {
            int idProduct = Integer.parseInt(product_id);
            ProductDetailsRequest request = productService.getProductById(idProduct);
            if (request != null) {
                System.out.println(request);
                return new ResponseEntity<>(request, HttpStatus.OK);
            }
            return new ResponseEntity<>(request, HttpStatus.NO_CONTENT);
        } catch (NumberFormatException e) {
            System.out.println(e);
            return new ResponseEntity<>("Invalid id product", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductCreationRequest request) {
        System.out.println(request.toString());
        System.out.println("Category id: " + request.getIdCategory());
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

            productService.createProduct(product, request.getIdCategory());
            System.out.println("product add: " + product.toString());
            return new ResponseEntity<>(product, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Failed to save driver: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-product/{product_id}")
    ResponseEntity<?> updateProduct(@RequestBody ProductUpdateRequest req, @PathVariable("product_id") String id) {
        try {
            int idProduct = Integer.parseInt(id);
            Category category = categoryService.findById(req.getIdCategory());
            if (category == null) {
                return new ResponseEntity<>("Category not found", HttpStatus.BAD_REQUEST);
            }
            Product product = productService.getProductByIdProduct(idProduct);
            Product productUpdate = productService.updateProduct(idProduct, category.getIdCategory(), req);
            System.out.println("Product id:" + id + "has been updated " + productUpdate.toString());
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Invalid product ID format", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{product_id}")
    void deleteProduct(@PathVariable("product_id") int id) {
        productService.deleteProduct(id);
    }
}
