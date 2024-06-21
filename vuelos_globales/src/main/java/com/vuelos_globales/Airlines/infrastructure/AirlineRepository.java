package com.vuelos_globales.Airlines.infrastructure;

import com.vuelos_globales.Airlines.domain.Airline;
import java.util.List;
import java.util.Optional;

public interface AirlineRepository {
    void save(Airline airline);
    void update(Airline airline);
    Optional<Airline> findById(int id); // Se usa optional debido a que puede devolver un valor null
    void detele(Airline airline);
    List<Airline> findAll();
}