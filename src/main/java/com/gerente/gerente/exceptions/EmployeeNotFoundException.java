package com.gerente.gerente.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Funcionário não encontrado");
    }
}
