package com.vuelos_globales.City.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.City.domain.City;

public interface CityRepository {
    void save(City city);
    void update(City city);
    Optional<City> findById(String id);
    void delete(String id);
    List<City> findAll();
}
