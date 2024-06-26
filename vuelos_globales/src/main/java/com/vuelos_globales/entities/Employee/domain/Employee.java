package com.vuelos_globales.entities.Employee.domain;

public class Employee {
    private String id;
    private String name;
    private String lastName;
    private String ingressDate;
    private String idRole;
    private String idAirline;
    private String idAirport;
    
    public Employee() {}

    public Employee(String id, String name, String lastName, String ingressDate, String idRole, String idAirline,
            String idAirport) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.ingressDate = ingressDate;
        this.idRole = idRole;
        this.idAirline = idAirline;
        this.idAirport = idAirport;
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

    public String getIngressDate() {
        return ingressDate;
    }

    public void setIngressDate(String ingressDate) {
        this.ingressDate = ingressDate;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(String idAirline) {
        this.idAirline = idAirline;
    }

    public String getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }
}
