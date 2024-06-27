package com.vuelos_globales.entities.TripBooking.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.BookingStatus.infrastructure.BookingStatusRepository;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;

public class TripBookingService {
    private final TripRepository tripRepository;
    private final TripBookingRepository tripBookingRepository;
    private final BookingStatusRepository bookingStatusRepository;
    
    public TripBookingService(TripBookingRepository tripBookingRepository, TripRepository tripRepository, BookingStatusRepository bookingStatusRepository) {
        this.tripBookingRepository = tripBookingRepository;
        this.tripRepository = tripRepository;
        this.bookingStatusRepository = bookingStatusRepository;
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


    // Trip 

    public void createTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public Optional<Trip> getTripById(String id) {
        return tripRepository.findById(id);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // BOOKIN STATUS

    public void createBookingStatus(BookingStatus bookingStatus) {
        bookingStatusRepository.save(bookingStatus);
    }

    public Optional<BookingStatus> getBookingStatusById(int id) {
        return bookingStatusRepository.findById(id);
    }

    public List<BookingStatus> getAllBookingStatuss() {
        return bookingStatusRepository.findAll();
    }

    //

    public void deleteTripBooking(String id){
        tripBookingRepository.delete(id);
    }

    public List<TripBooking> getAllTripBookings(){
        return tripBookingRepository.findAll();
    }
}