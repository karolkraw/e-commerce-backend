package com.example.ecommercebackend.Product.dto;

import com.example.ecommercebackend.ProductCategory.dto.ProductCategoryReadDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
public class ProductDto {
    private Long id;
    private ProductCategoryReadDto category;
    private String sku;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imageUrl;
    private boolean active;
    private int unitsInStock;
    private Date dateCreated;
    private Date lastUpdated;
}
