package com.vuelos_globales.FlightFares.infrastructure;

import java.util.Optional;
import java.util.List;

import com.vuelos_globales.FlightFares.domain.FlightFares;

public interface FlightFaresRepository {
    void save(FlightFares flightFares);
    void update(FlightFares flightFares);
    Optional<FlightFares> findById(String id);
    void delete(String id);
    List<FlightFares> findAll();
}
