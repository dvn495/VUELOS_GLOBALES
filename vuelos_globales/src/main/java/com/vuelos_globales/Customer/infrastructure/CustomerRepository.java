package com.vuelos_globales.Customer.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Customer.domain.Customer;

public interface CustomerRepository {
    void save(Customer customer);
    void update(Customer customer);
    Optional<Customer> findById(String id);
    void delete(String id);
    List<Customer> findAll();
}
