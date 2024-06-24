package com.vuelos_globales.FlightFares.application;

import java.util.Optional;
import java.util.List;

import com.vuelos_globales.FlightFares.domain.FlightFares;
import com.vuelos_globales.FlightFares.infrastructure.FlightFaresRepository;

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
