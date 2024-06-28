package com.vuelos_globales.entities.Customer.domain;

public class Customer {
    private String id;
    private String name;
    private String lastName;
    private int age;
    private int documentNumber;
    private int idDocumentType;
    
    public Customer() {}

    public Customer(String id, String name, String lastName, int age, int documentNumber, int idDocumentType) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.documentNumber = documentNumber;
        this.idDocumentType = idDocumentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public int getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    
    
    
}

