package com.vuelos_globales.entities.TripCrew.domain;

public class TripCrew {
    private String id;
    private String idEmployee;
    private String idConnection;

    public TripCrew() {}


    public TripCrew(String id, String idEmployee, String idConnection) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idConnection = idConnection;
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


    public String getIdConnection() {
        return idConnection;
    }


    public void setIdConnection(String idConnection) {
        this.idConnection = idConnection;
    }

    

}
