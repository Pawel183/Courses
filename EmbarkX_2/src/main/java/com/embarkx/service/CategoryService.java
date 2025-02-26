package com.embarkx.service;

import com.embarkx.payload.CategoryDTO;
import com.embarkx.payload.CategoryResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
}
