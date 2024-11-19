package com.gerente.gerente.controllers;

import com.gerente.gerente.domain.product.Product;
import com.gerente.gerente.domain.product.ProductCreateRequestDTO;
import com.gerente.gerente.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    public ProductService service;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.get(id));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductCreateRequestDTO data) {
        Product product = service.create(data);

        return ResponseEntity
                .status(201)
                .header("Location", "http://localhost:8080/products/" + product.getId())
                .body(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Map<String, Object> data
    ) {
        return ResponseEntity
                .status(200)
                .body(service.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
