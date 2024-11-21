package com.gerente.gerente.controllers;

import com.gerente.gerente.infra.cache.Cache;
import com.gerente.gerente.domain.transaction.Transaction;
import com.gerente.gerente.domain.transaction.TransactionRequestDTO;
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

    @Autowired
    private Cache<String, List<Transaction>> cache;

    @PostMapping("create")
    public ResponseEntity<Transaction> create(@RequestBody TransactionRequestDTO transaction) {
        return ResponseEntity.status(201).body(service.create(transaction));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }
}
