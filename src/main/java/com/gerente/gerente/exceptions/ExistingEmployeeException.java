package com.gerente.gerente.exceptions;

public class ExistingEmployeeException extends RuntimeException {
    public ExistingEmployeeException() {
        super("Funcionario já registrado");
    }
}
