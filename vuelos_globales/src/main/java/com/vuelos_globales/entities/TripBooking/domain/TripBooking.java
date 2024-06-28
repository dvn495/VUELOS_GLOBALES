package com.vuelos_globales.entities.TripBooking.domain;

import java.time.LocalDate;

public class TripBooking {
    private String id;
    private LocalDate bookinDate; // Revisar tipo de dato despues
    private String idTrip;
    private String idBookingStatus;
    private String idCustomer;

    public TripBooking() {}

    public TripBooking(String id, LocalDate bookinDate, String idTrip, String idBookingStatus, String idCustomer) {
        this.id = id;
        this.bookinDate = bookinDate;
        this.idTrip = idTrip;
        this.idBookingStatus = idBookingStatus;
        this.idCustomer = idCustomer;
    }

    // Getters y setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getBookingDate() {
        return bookinDate;
    }

    public void setBookingDate(LocalDate bookinDate) {
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

    // JAVI

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }
    
}
