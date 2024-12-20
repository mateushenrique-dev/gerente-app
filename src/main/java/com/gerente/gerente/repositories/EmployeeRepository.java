package com.gerente.gerente.repositories;

import com.gerente.gerente.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByNameIgnoreCase(String name);
}
