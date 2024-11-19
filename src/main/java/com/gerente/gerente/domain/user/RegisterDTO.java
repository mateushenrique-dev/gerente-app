package com.gerente.gerente.domain.user;

public record RegisterDTO(
        String login,
        String password,
        String email,
        UserRole role
) {}
