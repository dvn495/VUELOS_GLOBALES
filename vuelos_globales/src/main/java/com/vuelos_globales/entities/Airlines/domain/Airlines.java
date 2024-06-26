package com.vuelos_globales.entities.Airlines.domain;

public class Airlines {
    private String id;
    private String name;

    public Airlines(){}

    public Airlines(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Airlines(String name){
        this.name = name;
    }

    // GETTERS Y SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
