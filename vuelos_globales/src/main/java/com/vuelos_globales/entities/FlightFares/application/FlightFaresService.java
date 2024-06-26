package com.vuelos_globales.entities.FlightFares.application;

import java.util.Optional;

import com.vuelos_globales.entities.FlightFares.domain.FlightFares;
import com.vuelos_globales.entities.FlightFares.infrastructure.FlightFaresRepository;

import java.util.List;

public class FlightFaresService {
    private final FlightFaresRepository flightFaresRepository;

    public FlightFaresService(FlightFaresRepository flightFaresRepository){
        this.flightFaresRepository = flightFaresRepository;
    }

    public void createFlightFare(FlightFares flightFares){
        flightFaresRepository.save(flightFares);
    }

    public void updateFlightFare(FlightFares flightFares){
        flightFaresRepository.update(flightFares);
    }

    public Optional<FlightFares> getFlightFareById(String id){
        return flightFaresRepository.findById(id);
    }

    public void deleteFlighFare(String id){
        flightFaresRepository.delete(id);
    }

    public List<FlightFares> getAllFlightFares(){
        return flightFaresRepository.findAll();
    }
}
