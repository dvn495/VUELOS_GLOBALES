package com.vuelos_globales.entities.Trip.infrastructure;

import com.vuelos_globales.entities.Trip.domain.Trip;
import java.util.List;
import java.util.Optional;

public interface TripRepository {
    void save(Trip trip);
    void update(Trip trip);
    Optional<Trip> findById(String id);
    void delete(String id);
    List<Trip> findAll();
}
