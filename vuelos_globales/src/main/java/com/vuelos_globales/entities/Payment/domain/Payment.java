package com.vuelos_globales.entities.Payment.domain;

public class Payment {
    private String id;
    private double amount;
    private int idPaymentMethod;
    private String creditCardNumber;
    private String idTripBookingDetails;
    
    public Payment() {}

    public Payment(String id, double amount, int idPaymentMethod, String creditCardNumber, String idTripBookingDetails) {
        this.id = id;
        this.amount = amount;
        this.idPaymentMethod = idPaymentMethod;
        this.creditCardNumber = creditCardNumber;
        this.idTripBookingDetails = idTripBookingDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getIdTripBookingDetails() {
        return idTripBookingDetails;
    }

    public void setIdTripBookingDetails(String idTripBookingDetails) {
        this.idTripBookingDetails = idTripBookingDetails;
    }

    
    
}
