package com.vuelos_globales.entities.BookingStatus.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;

public interface BookingStatusRepository {
    void save(BookingStatus bookingStatus);
    Optional<BookingStatus> findById(int id);
    List<BookingStatus> findAll();
}
