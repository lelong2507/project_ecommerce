package com.example.ProjectBE.controller;

import com.example.ProjectBE.dto.request.CategoryDTO.CategoryCreationRequest;
import com.example.ProjectBE.entities.Category;
import com.example.ProjectBE.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:5173")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list-cate")
    List<Category> showAll() {
        return categoryService.showListCate();
    }
//
//    @PostMapping("/add-cate")
//    Category addCate(@RequestBody CategoryCreationRequest req) {
//        return categoryService.createNewCategory(req);
//    }

    @GetMapping("/{category_id}")
    Category findById(@PathVariable("category_id") int id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{category_id}")
    Category updateCategory(@PathVariable("category_id") int id, @RequestBody CategoryCreationRequest request) {
        return categoryService.updateCate(id, request);
    }

}
