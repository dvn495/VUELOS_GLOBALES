package com.vuelos_globales.entities.TripBooking.application;

import java.util.List;
import java.util.Optional;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;

public class TripBookingService {
    private final TripBookingRepository tripBookingRepository;


    public TripBookingService(TripBookingRepository tripBookingRepository){
        this.tripBookingRepository = tripBookingRepository;
    }

    public void createTripBooking(TripBooking tripBooking){
        tripBookingRepository.save(tripBooking);
    }

    public void updateTripBooking(TripBooking tripBooking){
        tripBookingRepository.update(tripBooking);

    }

    public Optional<TripBooking> getTripBookingById(String id){
        return tripBookingRepository.findById(id);
    }

    public void deleteTripBooking(String id){
        tripBookingRepository.delete(id);
    }

    public List<TripBooking> getAllTripBookings(){
        return tripBookingRepository.findAll();
    }
}