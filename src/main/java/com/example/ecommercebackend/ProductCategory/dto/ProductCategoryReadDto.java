package com.example.ecommercebackend.ProductCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class ProductCategoryReadDto {
    private Long id;
    private String categoryName;
}
