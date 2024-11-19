package com.gerente.gerente.domain.Transaction;

import java.time.LocalDateTime;

public record TransactionRequestDTO(
        Long productId,
        String type,
        Integer quantity,
        String description,
        String payment,
        Long employeeId
) {}
