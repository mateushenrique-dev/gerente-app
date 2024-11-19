package com.gerente.gerente.domain.Transaction;

import com.gerente.gerente.domain.Employee.Employee;
import com.gerente.gerente.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private TransactionType type;
    private Integer quantity;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    private String description;
    private TransactionPayment payment;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Transaction(
            Product product,
            TransactionType type,
            Integer quantity,
            LocalDateTime transactionDate,
            String description,
            TransactionPayment payment,
            Employee employee
    ) {
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
        this.description = description;
        this.payment = payment;
        this.employee = employee;
    }
}
