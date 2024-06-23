package com.vuelos_globales.FlightFares.domain;

public class FlightFares {
    private String id;
    private String description;
    private String details;
    private double value;
    
    public FlightFares(){}
    
    public FlightFares(String id, String description, String details, double value) {
        this.id = id;
        this.description = description;
        this.details = details;
        this.value = value;
    }

    // GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
