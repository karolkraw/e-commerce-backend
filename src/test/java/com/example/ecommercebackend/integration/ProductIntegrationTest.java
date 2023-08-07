package com.example.ecommercebackend.integration;

import com.example.ecommercebackend.Product.dto.ProductDto;
import com.example.ecommercebackend.ProductCategory.ProductCategory;
import com.example.ecommercebackend.ProductCategory.dto.ProductCategoryReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSaveProduct() {
        ProductDto productDto = ProductDto.builder()
                .name("Smartphone")
                .sku("ELECTRONIC123")
                .description("A high-end smartphone")
                .unitPrice(new BigDecimal("99.99"))
                .imageUrl("https://example.com/smartphone.jpg")
                .category(new ProductCategoryReadDto(1L, "Luggage Tags"))
                .active(true)
                .unitsInStock(100)
                .build();


        ResponseEntity<ProductCategory> responseEntity1 = restTemplate.getForEntity(
                "/api/categories/{id}", ProductCategory.class, 1);


        ResponseEntity<ProductDto> responseEntity = restTemplate.postForEntity(
                "/api/products", productDto, ProductDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ProductDto savedProduct = responseEntity.getBody();

        assertThat(savedProduct).isEqualTo(productDto);
    }
}
