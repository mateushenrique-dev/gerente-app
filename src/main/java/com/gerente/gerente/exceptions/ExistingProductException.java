package com.gerente.gerente.exceptions;

public class ExistingProductException extends RuntimeException {
    public ExistingProductException(String message) {
        super(message);
    }

    public ExistingProductException() {
        super("Produto já existente");
    }
}
