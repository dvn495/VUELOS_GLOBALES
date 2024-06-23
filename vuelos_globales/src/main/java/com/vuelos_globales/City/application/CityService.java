package com.vuelos_globales.City.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.City.domain.City;
import com.vuelos_globales.City.infrastructure.CityRepository;

public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void createCity(City city) {
        cityRepository.save(city);
    }

    public void updateCity(City city) {
        cityRepository.update(city);
    }

    public Optional<City> getCityById(String id) {
        return cityRepository.findById(id);
    }

    public void deleteCity(String id) {
        cityRepository.delete(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
