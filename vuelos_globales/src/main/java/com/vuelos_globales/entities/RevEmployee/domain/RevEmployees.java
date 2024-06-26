package com.vuelos_globales.entities.RevEmployee.domain;

public class RevEmployees {
    private String id;
    private String idEmployee;
    private String idRevision;

    public RevEmployees() {}

    public RevEmployees(String id, String idEmployee, String idRevision) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idRevision = idRevision;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(String idRevision) {
        this.idRevision = idRevision;
    }
}
