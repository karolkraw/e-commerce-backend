package com.example.ecommercebackend.Product;

import com.example.ecommercebackend.Product.dto.ProductDto;
import com.example.ecommercebackend.Product.dto.ProductDtoMapper;
import com.example.ecommercebackend.Product.Product;
import com.example.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
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
        return ProductDtoMapper.toDto(product);
    }

    @PostMapping
    public Product saveProduct(@RequestBody ProductDto product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}