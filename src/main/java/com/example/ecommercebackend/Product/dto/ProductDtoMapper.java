package com.example.ecommercebackend.Product.dto;

import com.example.ecommercebackend.Product.Product;
import com.example.ecommercebackend.ProductCategory.ProductCategory;

import static com.example.ecommercebackend.ProductCategory.dto.CategoryDtoMapper.mapCategoryReadDtoToCategory;
import static com.example.ecommercebackend.ProductCategory.dto.CategoryDtoMapper.mapCategoryToCategoryReadDto;

public class ProductDtoMapper {
    public static ProductDto mapProductToDto(Product product) {
        if (product == null) return null;

        ProductDto.ProductDtoBuilder builder = ProductDto.builder()
                .id(product.getId())
                .category(mapCategoryToCategoryReadDto(product.getCategory()))
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .imageUrl(product.getImageUrl())
                .active(product.isActive())
                .unitsInStock(product.getUnitsInStock())
                .dateCreated(product.getDateCreated())
                .lastUpdated(product.getLastUpdated());

        return builder.build();
    }

    public static Product mapDtoToProduct(Long id, ProductDto productDto) {
        if (productDto == null) return null;

        ProductCategory category = mapCategoryReadDtoToCategory(productDto.getCategory());

        return Product.builder()
                .id(id)
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

