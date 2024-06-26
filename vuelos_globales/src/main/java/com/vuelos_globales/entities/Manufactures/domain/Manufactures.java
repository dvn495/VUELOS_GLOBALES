package com.vuelos_globales.entities.Manufactures.domain;

public class Manufactures {
    private String id;
    private String manufacturer;

    public Manufactures(){}

    public Manufactures(String id, String manufacturer) {
        this.id = id;
        this.manufacturer = manufacturer;
    }

    // GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    } 
}
