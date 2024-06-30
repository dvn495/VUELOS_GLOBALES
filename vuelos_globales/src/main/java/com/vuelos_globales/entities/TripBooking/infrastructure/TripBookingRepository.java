package com.vuelos_globales.entities.TripBooking.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.FlightFares.domain.FlightFares;

public interface TripBookingRepository {
    void save(TripBooking tripBooking);
    void update(TripBooking tripBooking);
    Optional<TripBooking> findById(String id);
    void delete(String id);
    List<TripBooking> findAll();
    Optional<FlightFares> findFlightFareByTripBId(String id);
    List<String> findAllBookingTypes(); 
}
