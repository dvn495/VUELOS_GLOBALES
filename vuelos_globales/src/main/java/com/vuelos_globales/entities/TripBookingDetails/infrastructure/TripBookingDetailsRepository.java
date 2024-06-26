package com.vuelos_globales.entities.TripBookingDetails.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripBookingDetails.domain.TripBookingDetails;


public interface TripBookingDetailsRepository {
    void save(TripBookingDetails tripBookingDetail);
    void update(TripBookingDetails tripBookingDetails);
    Optional<TripBookingDetails> findById(String id);
    void delete(String id);
    List<TripBookingDetails> findAll();
}
