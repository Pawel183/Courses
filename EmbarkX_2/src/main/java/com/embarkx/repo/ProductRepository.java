package com.embarkx.repo;

import com.embarkx.model.Category;
import com.embarkx.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageable);
    Product findProductsByProductNameLikeIgnoreCase(String productName);
    Page<Product> findProductsByProductNameLikeIgnoreCase(String productName, Pageable pageable);
}
