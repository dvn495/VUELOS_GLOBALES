package com.vuelos_globales.entities.TripBooking.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripBooking.domain.TripBooking;

public interface TripBookingRepository {
    void save(TripBooking tripBooking);
    void update(TripBooking tripBooking);
    Optional<TripBooking> findById(String id);
    void delete(String id);
    List<TripBooking> findAll();
}
