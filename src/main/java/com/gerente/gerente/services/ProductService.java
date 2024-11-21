package com.gerente.gerente.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerente.gerente.infra.cache.UseCache;
import com.gerente.gerente.domain.product.Product;
import com.gerente.gerente.domain.product.ProductCreateRequestDTO;
import com.gerente.gerente.exceptions.ExistingProductException;
import com.gerente.gerente.exceptions.ProductNotFoundException;
import com.gerente.gerente.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public Product create(ProductCreateRequestDTO data) {
        Optional<Product> product = repository.findByNameIgnoreCase(data.name());

        if (product.isPresent()) {
            throw new ExistingProductException();
        }

        return repository.save(new Product(data));
    }

    @UseCache(key = "products")
    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product get(Long id) {
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product update(Long id, Map<String, Object> updates) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        Product product = objectMapper.convertValue(updates, Product.class);

        copyNonNullProperties(product, existingProduct);

        repository.save(existingProduct);

        return existingProduct;
    }

    public void delete(Long id) {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);

        repository.deleteById(product.getId());
    }

    private void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object source) {
        Map<String, Object> properties = objectMapper.convertValue(source, Map.class);

        String[] nonNullProperties = properties.entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);

        return Stream.concat(Arrays.stream(nonNullProperties), Stream.of("id"))
                .toArray(String[]::new);
    }
}
