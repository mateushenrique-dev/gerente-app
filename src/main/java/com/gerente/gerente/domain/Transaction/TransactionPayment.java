package com.gerente.gerente.domain.Transaction;

public enum TransactionPayment {
    CREDIT_CARD("Cartão de crédito"),
    DEBIT_CARD("Cartão de débito"),
    PIX("Pix"),
    MONEY("Dinheiro");

    private String payment;

    TransactionPayment(String payment) {
        this.payment = payment;
    }

    public static TransactionPayment fromPayment(String payment) {
        for (TransactionPayment transactionPayment : values()) {
            if (transactionPayment.payment.equalsIgnoreCase(payment)) {
                return transactionPayment;
            }
        }

        throw new IllegalArgumentException("Tipo inválido: " + payment);
    }
}
