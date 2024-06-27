package com.vuelos_globales.entities.Customer.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.Customer.infrastructure.CustomerRepository;
import com.vuelos_globales.entities.DocumentType.domain.DocumentType;
import com.vuelos_globales.entities.DocumentType.infrastructure.DocumentTypeRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public CustomerService(CustomerRepository customerRepository, DocumentTypeRepository documentTypeRepository) {
        this.customerRepository = customerRepository;
        this.documentTypeRepository = documentTypeRepository;

    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }
    

    // DOCUMENT TYPE

    public void createDocumentType(DocumentType documentType){
        documentTypeRepository.save(documentType);
    }

    public Optional<DocumentType> getDocumentTypeById(int id){
        return documentTypeRepository.findById(id);
    }

    public List<DocumentType> getAllDocumentTypes(){
        return documentTypeRepository.findAll();
    }

    //////

    public void deleteCustomer(String id) {
        customerRepository.delete(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
