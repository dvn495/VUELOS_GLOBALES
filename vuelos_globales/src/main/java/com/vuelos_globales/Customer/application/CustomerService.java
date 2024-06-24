package com.vuelos_globales.Customer.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Customer.domain.Customer;
import com.vuelos_globales.Customer.infrastructure.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

        public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCity(Customer customer) {
        customerRepository.update(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }
    
    public void deleteCustomer(String id) {
        customerRepository.delete(id);
    }

    public List<Customer> getAllCountries() {
        return customerRepository.findAll();
    }


}
