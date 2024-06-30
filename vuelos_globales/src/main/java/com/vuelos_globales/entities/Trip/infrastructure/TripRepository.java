package com.vuelos_globales.entities.Trip.infrastructure;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Trip.domain.Trip;

public interface TripRepository {
    void save(Trip trip);
    void update(Trip trip);
    Optional<Trip> findById(String id);
    void delete(String id);
    List<Trip> findAll();
    Optional<List<Trip>> findByParameters(LocalDate tripDate, String idCityA, String idCityB);
}
