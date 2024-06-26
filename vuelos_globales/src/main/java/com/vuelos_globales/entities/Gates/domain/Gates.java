package com.vuelos_globales.entities.Gates.domain;

public class Gates {   
    private String id;
    private String gate;
    private String idAirport;

    public Gates(){}

    public Gates(String id, String gate, String idAirport) {
        this.id = id;
        this.gate = gate;
        this.idAirport = idAirport;
    }

    // GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }
}
