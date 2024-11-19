package com.gerente.gerente.controllers;

import com.gerente.gerente.domain.Transaction.Transaction;
import com.gerente.gerente.domain.Transaction.TransactionRequestDTO;
import com.gerente.gerente.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping("create")
    public ResponseEntity<Transaction> create(@RequestBody TransactionRequestDTO transaction) {
        return ResponseEntity.status(201).body(service.create(transaction));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }
}
