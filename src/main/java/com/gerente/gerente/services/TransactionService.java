package com.gerente.gerente.services;

import com.gerente.gerente.infra.cache.UseCache;
import com.gerente.gerente.domain.employee.Employee;
import com.gerente.gerente.domain.transaction.Transaction;
import com.gerente.gerente.domain.transaction.TransactionPayment;
import com.gerente.gerente.domain.transaction.TransactionRequestDTO;
import com.gerente.gerente.domain.transaction.TransactionType;
import com.gerente.gerente.domain.product.Product;
import com.gerente.gerente.exceptions.EmployeeNotFoundException;
import com.gerente.gerente.exceptions.ProductNotFoundException;
import com.gerente.gerente.exceptions.TransactionNotCompletedException;
import com.gerente.gerente.repositories.EmployeeRepository;
import com.gerente.gerente.repositories.ProductRepository;
import com.gerente.gerente.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Transaction create(TransactionRequestDTO transactionData) {
        Product product = productRepository.findById(transactionData.productId())
                .orElseThrow(ProductNotFoundException::new);

        Employee employee = employeeRepository.findById(transactionData.employeeId())
                .orElseThrow(EmployeeNotFoundException::new);

        handleTransaction(product, transactionData);

        Transaction transaction = new Transaction(
                product,
                TransactionType.fromType(transactionData.type()),
                transactionData.quantity(),
                LocalDateTime.now(),
                transactionData.description(),
                TransactionPayment.fromPayment(transactionData.payment()),
                employee
        );

        return repository.save(transaction);
    }

    @UseCache(key = "transactions")
    public List<Transaction> listAll() {
        return repository.findAll();
    }

    private void notifyLowStock() {

    }

    private void handleTransaction(Product product, TransactionRequestDTO transaction) {
        if (TransactionType.fromType(transaction.type()) == TransactionType.OUTFLOW) {
            if (product.getQuantity() < transaction.quantity())
                throw new TransactionNotCompletedException("Quantidade de venda menor do que quantidade disponível no estoque.");

            product.setQuantity(product.getQuantity() - transaction.quantity());
        } else {
            product.setQuantity(product.getQuantity() + transaction.quantity());
        }

        if (product.getQuantity() < product.getMinQuantity()) notifyLowStock();
    }
}
