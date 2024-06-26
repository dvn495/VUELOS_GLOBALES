package com.vuelos_globales.entities.Payment.domain;

public class Payment {
    private String id;
    private int amount;
    private int idPaymentMethod;
    private int creditCardNumber;
    private String idTripBookingDetails;
    
    public Payment() {}

    public Payment(String id, int amount, int idPaymentMethod, int creditCardNumber, String idTripBookingDetails) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getIdTripBookingDetails() {
        return idTripBookingDetails;
    }

    public void setIdTripBookingDetails(String idTripBookingDetails) {
        this.idTripBookingDetails = idTripBookingDetails;
    }

    
    
}
