package com.example.ecommercebackend.ProductCategory.dto;

import com.example.ecommercebackend.Product.Product;
import com.example.ecommercebackend.Product.dto.ProductDto;
import com.example.ecommercebackend.ProductCategory.ProductCategory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CategoryDtoMapper {
    public static ProductCategoryReadDto mapCategoryToCategoryReadDto(ProductCategory category) {
        if (category == null) return null;
        return new ProductCategoryReadDto(category.getId(), category.getCategoryName());
    }

    public  static ProductCategory mapCategoryReadDtoToCategory(ProductCategoryReadDto categoryDto) {
        if (categoryDto == null) return null;

        ProductCategory.ProductCategoryBuilder builder = ProductCategory.builder()
                .id(categoryDto.getId())
                .categoryName(categoryDto.getCategoryName())
                .products(new ArrayList<>());

        return builder.build();
    }

    public static Product mapDtoToProduct(ProductDto productDto) {
        if (productDto == null) return null;

        ProductCategory category = mapCategoryReadDtoToCategory(productDto.getCategory());

        return Product.builder()
                .id(productDto.getId())
                .category(category)
                .sku(productDto.getSku())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .unitPrice(productDto.getUnitPrice())
                .imageUrl(productDto.getImageUrl())
                .active(productDto.isActive())
                .unitsInStock(productDto.getUnitsInStock())
                .dateCreated(productDto.getDateCreated())
                .lastUpdated(productDto.getLastUpdated())
                .build();
    }
}
