package com.gerente.gerente.domain.transaction;

public enum TransactionType {
    INFLOW("Entrada"),
    OUTFLOW("Saída");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }

    public static TransactionType fromType(String type) {
        for (TransactionType transactionType : values()) {
            if (transactionType.type.equalsIgnoreCase(type)) {
                return transactionType;
            }
        }

        throw new IllegalArgumentException("Tipo inválido: " + type);
    }
}
