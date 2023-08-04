package com.example.ecommercebackend.service;

import com.example.ecommercebackend.entity.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getAllCategories();
    ProductCategory getCategoryById(Long id);
    ProductCategory saveCategory(ProductCategory category);
    void deleteCategory(Long id);
}
