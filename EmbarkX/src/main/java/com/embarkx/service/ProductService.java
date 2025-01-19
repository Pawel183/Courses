package com.embarkx.service;

import com.embarkx.model.Product;
import com.embarkx.payload.ProductDTO;
import com.embarkx.payload.ProductResponse;


public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
