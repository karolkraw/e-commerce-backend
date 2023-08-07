package com.example.ecommercebackend.integration;

import com.example.ecommercebackend.Product.dto.ProductDto;
import com.example.ecommercebackend.ProductCategory.dto.ProductCategoryReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSaveProduct() {
        ProductDto expectedProduct = ProductDto.builder()
                .name("Smartphone")
                .sku("ELECTRONIC123")
                .description("A high-end smartphone")
                .unitPrice(new BigDecimal("99.99"))
                .imageUrl("https://example.com/smartphone.jpg")
                .category(new ProductCategoryReadDto(1L, "Luggage Tags"))
                .active(true)
                .unitsInStock(100)
                .build();


        ResponseEntity<ProductDto> responseEntity = saveProduct(expectedProduct);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ProductDto actualProduct = responseEntity.getBody();
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test
    public void testGetProductById_ReturnsProductDto() {
        final long id = 1;
        ProductDto expectedProduct = ProductDto.builder()
                .id(id)
                .category(new ProductCategoryReadDto(id, "Luggage Tags"))
                .sku("BOOK-TECH-1000")
                .name("Crash Course in Python")
                .description("Learn Python at your own pace.")
                .imageUrl("assets/images/products/books/book-luv2code-1000.png")
                .active(true)
                .unitsInStock(100)
                .unitPrice(new BigDecimal("14.99"))
                .build();

        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity(
                "/api/products/{id}", ProductDto.class, id);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ProductDto actualProduct = responseEntity.getBody();
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test
    public void testGetProductById_IdNotFound_Returns404() {
        final long nonExistentId = 9999;

        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity(
                "/api/products/{id}", ProductDto.class, nonExistentId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllProducts() {
        final List<ProductDto> products = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            ProductDto product = ProductDto.builder()
                    .id((long) i)
                    .sku("BOOK-TECH-" + (1001 + i))
                    .name("Product Name " + (i))
                    .description("Product Description " + (i))
                    .imageUrl("assets/images/products/books/book-" + (1001 + i) + ".png")
                    .category(new ProductCategoryReadDto(1L, "Luggage Tags"))
                    .active(true)
                    .unitsInStock(100)
                    .unitPrice(new BigDecimal("20.99"))
                    .build();

            products.add(product);
        }

        products.forEach(this::saveProduct);

        ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange(
                "/api/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<ProductDto> actualProducts = responseEntity.getBody();
        assertThat(actualProducts).isNotNull();
        assertThat(actualProducts).hasSameSizeAs(products);

        IntStream.range(0, products.size())
                .forEach(i -> assertThat(actualProducts.get(i)).isEqualTo(products.get(i)));
    }

    private ResponseEntity<ProductDto> saveProduct(ProductDto expectedProduct) {
        return restTemplate.postForEntity(
                "/api/products", expectedProduct, ProductDto.class);
    }
}
