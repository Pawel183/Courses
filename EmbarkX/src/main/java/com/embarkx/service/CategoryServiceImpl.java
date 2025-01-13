package com.embarkx.service;

import com.embarkx.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    public List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId);
        nextId++;
        categories.add(category);
    }

    @Override
    public String deleteCategory(long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categories.remove(category);

        return "Category with categoryId: " + categoryId + " deleted successfully!";
    }

    @Override
    public String updateCategory(long categoryId, Category category) {
        Category categoryToUpdate = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categoryToUpdate.setCategoryName(category.getCategoryName());

        return "Category with categoryId: " + categoryId + " updated successfully!";
    }
}
