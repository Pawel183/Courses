package com.embarkx.controller;

import com.embarkx.model.Category;
import com.embarkx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/public/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/api/public/categories")
    public String addCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return "Category added successfully";
    }

}
