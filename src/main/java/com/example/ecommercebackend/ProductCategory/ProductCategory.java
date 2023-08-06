package com.example.ecommercebackend.ProductCategory;

import com.example.ecommercebackend.Product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> products;
}
