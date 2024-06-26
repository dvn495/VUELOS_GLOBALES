package com.vuelos_globales.entities.Planes.domain;

import java.time.LocalDate;


public class Planes {
    private String id;
    private String plates;
    private int capacity;
    private LocalDate fabricationDate;
    private String idModel;
    private String idStatus;

    public Planes(){}

    public Planes(String id, String plates, int capacity, LocalDate fabricationDate, String idModel, String idStatus) {
        this.id = id;
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idModel = idModel;
        this.idStatus = idStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate (LocalDate fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    
}
