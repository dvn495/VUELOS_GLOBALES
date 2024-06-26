package com.vuelos_globales.entities.TripBooking.domain;


public class TripBooking {
    private String id;
    private String bookinDate; // Revisar tipo de dato despues
    private String idTrip;
    private String idBookingStatus;

    public TripBooking() {}

    public TripBooking(String id, String bookinDate, String idTrip, String idBookingStatus) {
        this.id = id;
        this.bookinDate = bookinDate;
        this.idTrip = idTrip;
        this.idBookingStatus = idBookingStatus;
    }

    // Getters y setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookinDate;
    }

    public void setBookingDate(String bookinDate) {
        this.bookinDate = bookinDate;
    }

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getIdBookingStatus() {
        return idBookingStatus;
    }

    public void setIdBookingStatus(String idBookingStatus) {
        this.idBookingStatus = idBookingStatus;
    }    
    
    
}
