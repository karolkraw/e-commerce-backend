package com.example.ecommercebackend.Product;

import com.example.ecommercebackend.Product.dto.ProductDto;
import com.example.ecommercebackend.Product.dto.ProductDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommercebackend.Product.dto.ProductDtoMapper.mapDtoToProduct;
import static com.example.ecommercebackend.Product.dto.ProductDtoMapper.mapProductToDto;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Long EMPTY_ID = 0L;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return mapProductToDto(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        Product product = productService.saveProduct(mapDtoToProduct(EMPTY_ID, productDto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapProductToDto(product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}