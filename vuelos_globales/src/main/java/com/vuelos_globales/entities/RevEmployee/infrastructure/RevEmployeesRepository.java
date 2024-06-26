package com.vuelos_globales.entities.RevEmployee.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.RevEmployee.domain.RevEmployees;

public interface RevEmployeesRepository {
    void save(RevEmployees revEmployees);
    void update(RevEmployees revEmployees);
    Optional<RevEmployees> findById(String id);
    void delete(String id);
    List<RevEmployees> findAll();
}
