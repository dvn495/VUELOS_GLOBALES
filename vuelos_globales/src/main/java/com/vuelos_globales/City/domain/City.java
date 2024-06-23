package com.vuelos_globales.City.domain;

public class City {
    private String id;
    private String cityName;
    private String countryCity;
    
    public City() {}

    public City(String id, String cityName, String countryCity) {
        this.id = id;
        this.cityName = cityName;
        this.countryCity = countryCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCity() {
        return countryCity;
    }

    public void setCountryCity(String countryCity) {
        this.countryCity = countryCity;
    }
}
