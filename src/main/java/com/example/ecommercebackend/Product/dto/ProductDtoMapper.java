package com.example.ecommercebackend.Product.dto;

import com.example.ecommercebackend.Product.Product;
import com.example.ecommercebackend.ProductCategory.ProductCategory;

public class ProductDtoMapper {
    public static ProductDto mapProductToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setCategoryId(product.getCategory().getId());
        dto.setSku(product.getSku());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setActive(product.isActive());
        dto.setUnitsInStock(product.getUnitsInStock());
        dto.setDateCreated(product.getDateCreated());
        dto.setLastUpdated(product.getLastUpdated());
        return dto;
    }

    public static Product dtoToProduct(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());

        ProductCategory category = getProductCategoryFromId(dto.getCategoryId());
        product.setCategory(category);

        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setUnitPrice(dto.getUnitPrice());
        product.setImageUrl(dto.getImageUrl());
        product.setActive(dto.isActive());
        product.setUnitsInStock(dto.getUnitsInStock());
        product.setDateCreated(dto.getDateCreated());
        product.setLastUpdated(dto.getLastUpdated());
        return product;
    }

}

