package com.gerente.gerente.domain.product;

import com.gerente.gerente.domain.Transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "products")
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    @Column(name = "min_quantity")
    private Integer minQuantity;
    private ProductCategory category;

    public Product(ProductCreateRequestDTO product) {
        name = product.name();
        price = product.price();
        quantity = product.quantity();
        minQuantity = product.minQuantity();
        category = product.category();
    }
}
