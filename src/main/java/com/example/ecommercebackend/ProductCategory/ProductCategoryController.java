package com.example.ecommercebackend.ProductCategory;

import com.example.ecommercebackend.ProductCategory.ProductCategory;
import com.example.ecommercebackend.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {
    private final ProductCategoryService categoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ProductCategory getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ProductCategory saveCategory(@RequestBody ProductCategory category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}