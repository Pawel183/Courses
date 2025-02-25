package com.embarkx.service;

import com.embarkx.model.Category;
import com.embarkx.payload.CategoryDTO;
import com.embarkx.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long categoryId);

    String updateCategory(Long categoryId, Category category);
}
