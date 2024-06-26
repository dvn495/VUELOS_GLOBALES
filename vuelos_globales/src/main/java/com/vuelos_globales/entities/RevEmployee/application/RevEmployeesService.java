package com.vuelos_globales.entities.RevEmployee.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.RevEmployee.infrastructure.RevEmployeesRepository;
import com.vuelos_globales.entities.RevEmployee.domain.RevEmployees;

public class RevEmployeesService {
    private final RevEmployeesRepository revEmployeesRepository;

    public RevEmployeesService(RevEmployeesRepository revEmployeesRepository) {
        this.revEmployeesRepository = revEmployeesRepository;
    }

    public void createRevEmployee(RevEmployees revEmployee) {
        revEmployeesRepository.save(revEmployee);
    }

    public void updateRevEmployee(RevEmployees revEmployee) {
        revEmployeesRepository.update(revEmployee);
    }

    public Optional<RevEmployees> findById(String id) {
        return revEmployeesRepository.findById(id);
    }

    public void deleteRevEmployee(String id) {
        revEmployeesRepository.delete(id);
    }
    
    public List<RevEmployees> getAllRevEmployees() {
        return revEmployeesRepository.findAll();
    }
}
