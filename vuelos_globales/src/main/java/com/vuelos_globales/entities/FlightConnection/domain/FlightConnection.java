package com.vuelos_globales.entities.FlightConnection.domain;

public class FlightConnection {
    private String id;
    private int connectionOrder;
    private String idTrip;
    private String idPlane;
    private String idAirportA;
    private String idArportB;
    
    public FlightConnection() {}

    public FlightConnection(String id, int connectionOrder, String idTrip, String idPlane, String idAirportA,
            String idArportB) {
        this.id = id;
        this.connectionOrder = connectionOrder;
        this.idTrip = idTrip;
        this.idPlane = idPlane;
        this.idAirportA = idAirportA;
        this.idArportB = idArportB;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getConnectionOrder() {
        return connectionOrder;
    }

    public void setConnectionOrder(int connectionOrder) {
        this.connectionOrder = connectionOrder;
    }

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(String idPlane) {
        this.idPlane = idPlane;
    }

    public String getIdAirportA() {
        return idAirportA;
    }

    public void setIdAirportA(String idAirportA) {
        this.idAirportA = idAirportA;
    }

    public String getIdArportB() {
        return idArportB;
    }

    public void setIdArportB(String idArportB) {
        this.idArportB = idArportB;
    }
}
