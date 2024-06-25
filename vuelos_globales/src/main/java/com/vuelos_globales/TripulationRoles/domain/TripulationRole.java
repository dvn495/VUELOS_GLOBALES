package com.vuelos_globales.TripulationRoles.domain;

public class TripulationRole {
    private String id;
    private String role;

    public TripulationRole() {}

    public TripulationRole(String id, String role) {
        this.id = id;
        this.role = role;
    }

    //Getter y Setters
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}