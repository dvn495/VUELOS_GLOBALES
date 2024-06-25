package com.vuelos_globales.TripBookingDetails.domain;

public class TripBookingDetails {
    private String id;
    private String idTripBooking;
    private String idCustomer;
    private String idFlightFares;

    public TripBookingDetails() {}

    public TripBookingDetails(String id, String idTripBooking, String idCustomer, String idFlightFares) {
        this.id = id;
        this.idTripBooking = idTripBooking;
        this.idCustomer = idCustomer;
        this.idFlightFares = idFlightFares;
    }

    // Getters y setters
    
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getIdTripBooking() {
        return idTripBooking;
    }


    public void setIdTripBooking(String idTripBooking) {
        this.idTripBooking = idTripBooking;
    }


    public String getIdCustomer() {
        return idCustomer;
    }


    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }


    public String getIdFlightFares() {
        return idFlightFares;
    }


    public void setIdFlightFares(String idFlightFares) {
        this.idFlightFares = idFlightFares;
    }

            
    
}
