package com.vuelos_globales.entities.FlightFares.infrastructure;

import java.util.Optional;

import com.vuelos_globales.entities.FlightFares.domain.FlightFares;

import java.util.List;

public interface FlightFaresRepository {
    void save(FlightFares flightFares);
    void update(FlightFares flightFares);
    Optional<FlightFares> findById(String id);
    void delete(String id);
    List<FlightFares> findAll();
}
