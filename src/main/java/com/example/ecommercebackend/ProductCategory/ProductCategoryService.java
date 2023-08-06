package com.example.ecommercebackend.ProductCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ProductCategory getCategoryById(Long id) {
        Optional<ProductCategory> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ProductCategory saveCategory(ProductCategory category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}