package com.vuelos_globales.entities.Trip.domain;

import java.time.LocalDate;

public class Trip {
    private String id;
    private LocalDate tripDate;
    private double price;

    public Trip(String id, LocalDate tripDate, double price) {
        this.id = id;
        this.tripDate = tripDate;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
