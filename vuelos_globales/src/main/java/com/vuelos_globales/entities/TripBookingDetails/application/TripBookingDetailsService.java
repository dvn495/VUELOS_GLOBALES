package com.vuelos_globales.entities.TripBookingDetails.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripBookingDetails.domain.TripBookingDetails;
import com.vuelos_globales.entities.TripBookingDetails.infrastructure.TripBookingDetailsRepository;

public class TripBookingDetailsService {
    private final TripBookingDetailsRepository tripBookingDetailsRepository;

    public TripBookingDetailsService(TripBookingDetailsRepository tripBookingDetailsRepository){
        this.tripBookingDetailsRepository = tripBookingDetailsRepository;
    }

    public void createTripBookingDetails(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.save(tripBookingDetails);
    }

    public void updateTripBookingDetails(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.update(tripBookingDetails);
    }

    public Optional<TripBookingDetails> getTripBookingDetailsById(String id){
        return tripBookingDetailsRepository.findById(id);
    }

    public void deleteTripBookingDetails(String id){
        tripBookingDetailsRepository.delete(id);
    }

    public List<TripBookingDetails> getAllTripBookingDetails(){
        return tripBookingDetailsRepository.findAll();
    }
}
