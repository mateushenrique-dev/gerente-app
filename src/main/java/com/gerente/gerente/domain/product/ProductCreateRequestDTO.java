package com.gerente.gerente.domain.product;

public record ProductCreateRequestDTO(
        String name,
        int price,
        int quantity,
        int minQuantity,
        ProductCategory category
) {}
