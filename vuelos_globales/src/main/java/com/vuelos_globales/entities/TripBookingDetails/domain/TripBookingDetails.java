package com.vuelos_globales.entities.TripBookingDetails.domain;

public class TripBookingDetails {
    private String id;
    private int seatNumber;
    private String idTripBooking;
    private String idFlightFares;

    public TripBookingDetails() {}

    public TripBookingDetails(String id, int seatNumber, String idTripBooking, String idFlightFares) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.idTripBooking = idTripBooking;
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

    public String getIdFlightFares() {
        return idFlightFares;
    }


    public void setIdFlightFares(String idFlightFares) {
        this.idFlightFares = idFlightFares;
    }

    // JAVI

    public int getSeatNumber() {
        return seatNumber;
    }


    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    
}
