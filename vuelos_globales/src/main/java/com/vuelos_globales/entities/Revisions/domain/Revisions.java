package com.vuelos_globales.entities.Revisions.domain;

import java.sql.Date;



public class Revisions {
    private String id;
    private Date revisionDate; // Toca arreglar el tipo de dato, ya que es una fecha, aunque investigando decia que en lugar de String se podia utilizar Date para evitar problemas de formato
    private String idPlane;
    private String idDetails;

    public Revisions() {}
    
    public Revisions(String id, Date revisionDate, String idPlane, String idDetails) {
        this.id = id;
        this.revisionDate = revisionDate;
        this.idPlane = idPlane;
        this.idDetails = idDetails;
    }

    
    // Getters y setters
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(String idPlane) {
        this.idPlane = idPlane;
    }

    public String getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(String idDetails) {
        this.idDetails = idDetails;
    }


}
