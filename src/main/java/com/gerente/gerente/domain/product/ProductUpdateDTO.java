package com.gerente.gerente.domain.product;

public record ProductUpdateDTO(
        String name,
        Integer price,
        Integer quantity,
        Integer minQuantity,
        ProductCategory category
) {
}
