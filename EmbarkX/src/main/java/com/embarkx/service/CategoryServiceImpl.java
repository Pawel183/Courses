package com.embarkx.service;

import com.embarkx.exceptions.ApiException;
import com.embarkx.exceptions.ResourceNotFoundException;
import com.embarkx.model.Category;
import com.embarkx.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new ApiException("No categories created till now");
        }

        return categories;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if (savedCategory != null) {
            throw new ApiException("Category with the name " + category.getCategoryName() + " already exist");
        }

        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryRepository.delete(category);

        return "Category with categoryId: " + categoryId + " deleted successfully!";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) {
        Category existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if (existingCategory != null) {
            throw new ApiException("Category with the name " + category.getCategoryName() + " already exist");
        }

        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        categoryToUpdate.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryToUpdate);

        return "Category with categoryId: " + categoryId + " updated successfully!";
    }
}
