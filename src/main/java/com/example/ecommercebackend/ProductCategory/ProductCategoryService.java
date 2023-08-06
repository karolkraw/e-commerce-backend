package com.example.ecommercebackend.ProductCategory;

import com.example.ecommercebackend.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService implements ProductCategoryService {
    private final ProductCategoryRepository categoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ProductCategory getCategoryById(Long id) {
        Optional<ProductCategory> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public ProductCategory saveCategory(ProductCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}