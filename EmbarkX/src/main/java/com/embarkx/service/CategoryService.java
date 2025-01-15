package com.embarkx.service;

import com.embarkx.model.Category;
import com.embarkx.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);

    String updateCategory(Long categoryId, Category category);
}
