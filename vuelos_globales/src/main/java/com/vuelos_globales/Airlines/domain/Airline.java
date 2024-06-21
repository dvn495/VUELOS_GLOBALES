package com.vuelos_globales.Airlines.domain;

public class Airline {
    String id;
    String airline;
    
    public Airline() {}

    public Airline(String id, String airline) {
        this.id = id;
        this.airline = airline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
