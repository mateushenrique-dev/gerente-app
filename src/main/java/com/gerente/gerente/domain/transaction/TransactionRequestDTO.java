package com.gerente.gerente.domain.transaction;

public record TransactionRequestDTO(
        Long productId,
        String type,
        Integer quantity,
        String description,
        String payment,
        Long employeeId
) {}
