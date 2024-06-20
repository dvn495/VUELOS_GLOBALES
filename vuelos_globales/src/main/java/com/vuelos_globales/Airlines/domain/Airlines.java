package com.vuelos_globales.Airlines.domain;

public class Airlines {
    private int id;
    private String name;

    public Airlines(){}

    public Airlines(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Airlines(String name){
        this.name = name;
    }

    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
