package com.vuelos_globales.entities.Trip.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Airport.domain.Airport;
import com.vuelos_globales.entities.Airport.infrastructure.AirportRepository;
import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.BookingStatus.infrastructure.BookingStatusRepository;
import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.Customer.infrastructure.CustomerRepository;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;

public class TripService {

    private final TripRepository tripRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final TripBookingRepository tripBookingRepository;
    private final AirportRepository airportRepository;
    private final CustomerRepository customerRepository;


    public TripService(TripRepository tripRepository, BookingStatusRepository bookingStatusRepository, TripBookingRepository tripBookingRepository,
    AirportRepository airportRepository, CustomerRepository customerRepository) {
        this.tripRepository = tripRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.tripBookingRepository = tripBookingRepository;
        this.airportRepository = airportRepository;
        this.customerRepository = customerRepository;
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

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    

}