package com.vuelos_globales.TripBookingDetails.infrastructure;

import com.vuelos_globales.TripBookingDetails.domain.TripBookingDetails;
import java.util.List;
import java.util.Optional;


public interface TripBookingDetailsRepository {
    void save(TripBookingDetails tripBookingDetail);
    void update(TripBookingDetails tripBookingDetails);
    Optional<TripBookingDetails> findById(String id);
    void delete(String id);
    List<TripBookingDetails> findAll();
}
