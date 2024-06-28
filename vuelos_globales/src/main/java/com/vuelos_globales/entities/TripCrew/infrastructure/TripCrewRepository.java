package com.vuelos_globales.entities.TripCrew.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripCrew.domain.TripCrew;

public interface TripCrewRepository {
    void save (TripCrew tripCrew);
    void update(TripCrew tripCrew);
    Optional<TripCrew> findById(String id);
    void delete(String id);
    List<TripCrew> findAll();
    Optional<TripCrew> findByIdTrip(String id);
}
