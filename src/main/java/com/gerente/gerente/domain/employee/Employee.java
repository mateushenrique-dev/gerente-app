package com.gerente.gerente.domain.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Float salary;

    public Employee(EmployeeRequestDTO employeeData) {
        name = employeeData.name();
        salary = employeeData.salary();
    }
}
