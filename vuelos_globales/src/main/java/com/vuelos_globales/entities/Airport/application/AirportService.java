package com.vuelos_globales.entities.Airport.application;

import java.util.Optional;

import com.vuelos_globales.entities.Airport.domain.Airport;
import com.vuelos_globales.entities.Airport.infrastructure.AirportRepository;

import java.util.List;

public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void createAirport(Airport airport) {
        airportRepository.save(airport);
    }
    
    public void updateAirport(Airport airport) {
        airportRepository.update(airport);
    }

    public Optional<Airport> getAirportById(String id) {
        return airportRepository.findById(id);
    }

    public void deleteAirport(String id) {
        airportRepository.delete(id);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}
