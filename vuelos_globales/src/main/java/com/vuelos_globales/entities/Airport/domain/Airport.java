package com.vuelos_globales.entities.Airport.domain;

public class Airport {
    private String id;
    private String airportName;
    public String airportCity;
    
    public Airport() {}

    public Airport(String id, String airportName, String airportCity) {
        this.id = id;
        this.airportName = airportName;
        this.airportCity = airportCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    
    
}
