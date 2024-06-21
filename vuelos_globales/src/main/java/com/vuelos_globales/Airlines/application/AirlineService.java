package com.vuelos_globales.Airlines.application;

import com.vuelos_globales.Airlines.domain.Airline;
import com.vuelos_globales.Airlines.infrastructure.AirlineRepository;
import java.util.List;
import java.util.Optional;

public class AirlineService {
    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public void save(Airline airline) {
        airlineRepository.save(airline);
    }

    public void update(Airline airline) {
        airlineRepository.update(airline);
    }

    public Optional<Airline> findById(int id) {
        return airlineRepository.findById(id);
    }

    public void detele(Airline airline) {
        airlineRepository.detele(airline);
    }

    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    
}
