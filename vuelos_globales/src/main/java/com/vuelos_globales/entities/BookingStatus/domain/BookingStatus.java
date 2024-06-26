package com.vuelos_globales.entities.BookingStatus.domain;

public class BookingStatus {
    private int id;
    private String bookingStatus;
    
    public BookingStatus() {}

    public BookingStatus(int id, String bookingStatus) {
        this.id = id;
        this.bookingStatus = bookingStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
