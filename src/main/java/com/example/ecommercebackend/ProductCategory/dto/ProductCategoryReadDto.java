package com.example.ecommercebackend.ProductCategory.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductCategoryReadDto {
    private Long id;
    private String categoryName;
}
