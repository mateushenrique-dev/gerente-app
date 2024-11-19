package com.gerente.gerente.exceptions;

public class TransactionNotCompletedException extends RuntimeException {
    public TransactionNotCompletedException(String message) {
        super(message);
    }
}
