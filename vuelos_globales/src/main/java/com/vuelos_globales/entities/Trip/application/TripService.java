package com.vuelos_globales.entities.Trip.application;

import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;
import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.BookingStatus.infrastructure.BookingStatusRepository;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;

import java.util.List;
import java.util.Optional;

public class TripService {

    private final TripRepository tripRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final TripBookingRepository tripBookingRepository;


    public TripService(TripRepository tripRepository, BookingStatusRepository bookingStatusRepository, TripBookingRepository tripBookingRepository) {
        this.tripRepository = tripRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.tripBookingRepository = tripBookingRepository;
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
    
    // TRIP BOOKING

    public void createTripBooking(TripBooking tripBooking){
        tripBookingRepository.save(tripBooking);
    }

    public Optional<TripBooking> getTripBookingById(String id){
        return tripBookingRepository.findById(id);
    }

    public List<TripBooking> getAllTripBookings(){
        return tripBookingRepository.findAll();
    }


    //

    public void deleteTrip(String id) {
        tripRepository.delete(id);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

}