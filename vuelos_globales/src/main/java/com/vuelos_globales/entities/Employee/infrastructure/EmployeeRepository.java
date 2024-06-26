package com.vuelos_globales.entities.Employee.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Employee.domain.Employee;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    Optional<Employee> findById(String id);
    void delete(String id);
    List<Employee> findAll();
}
