package com.vuelos_globales.entities.Trip.application;

import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;
import java.util.List;
import java.util.Optional;

public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void createTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public void updateTrip(Trip trip) {
        tripRepository.update(trip);
    }

    public Optional<Trip> getTripById(String id) {
        return tripRepository.findById(id);
    }

    public void deleteTrip(String id) {
        tripRepository.delete(id);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

}