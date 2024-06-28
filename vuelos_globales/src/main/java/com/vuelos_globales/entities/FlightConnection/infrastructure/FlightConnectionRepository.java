package com.vuelos_globales.entities.FlightConnection.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;

public interface FlightConnectionRepository {
    void save(FlightConnection flightConnection);
    void update(FlightConnection flightConnection);
    Optional<FlightConnection> findById(String id);
    void delete(String id);
    List<FlightConnection> findAll();
    Optional<FlightConnection> findByTrip(String id);
}
