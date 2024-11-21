package com.gerente.gerente.controllers;

import com.gerente.gerente.domain.Employee.Employee;
import com.gerente.gerente.domain.Employee.EmployeeRequestDTO;
import com.gerente.gerente.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody EmployeeRequestDTO employeeData) {
        return ResponseEntity.status(201).body(service.create(employeeData));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> get() {
        return ResponseEntity.status(200).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get (@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.get(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Map<String, Object> employeeData) {
        return ResponseEntity.status(200).body(service.update(id, employeeData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(200).build();
    }
}
