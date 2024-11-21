package com.gerente.gerente.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerente.gerente.infra.cache.UseCache;
import com.gerente.gerente.domain.employee.Employee;
import com.gerente.gerente.domain.employee.EmployeeRequestDTO;
import com.gerente.gerente.exceptions.EmployeeNotFoundException;
import com.gerente.gerente.exceptions.ExistingEmployeeException;
import com.gerente.gerente.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public Employee create(EmployeeRequestDTO employeeData) {
        Optional<Employee> existingEmployee = repository.findByNameIgnoreCase(employeeData.name());
        if (existingEmployee.isPresent()) throw new ExistingEmployeeException();

        Employee employee = new Employee(employeeData);
        repository.save(employee);

        return employee;
    }

    @UseCache(key = "employees")
    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee get(Long id) {
        return repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public void delete(Long id) {
        Employee employee = repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        repository.deleteById(id);
    }

    public Employee update(Long id, Map<String, Object> employeeData) {
        Employee existingEmployee = repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        Employee employee = objectMapper.convertValue(employeeData, Employee.class);

        copyNonNullProperties(employee, existingEmployee);

        repository.save((existingEmployee));

        return existingEmployee;
    }

    private void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object source) {
        Map<String, Object> properties = objectMapper.convertValue(source, Map.class);

        return properties.entrySet().stream()
                .filter(entry -> entry.getValue() == null)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }
}
