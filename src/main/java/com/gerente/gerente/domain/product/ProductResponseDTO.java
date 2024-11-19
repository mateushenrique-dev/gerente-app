package com.gerente.gerente.domain.product;

public record ProductResponseDTO(
   String name,
   int price,
   int quantity,
   int minQuantity,
   ProductCategory category
) {}
