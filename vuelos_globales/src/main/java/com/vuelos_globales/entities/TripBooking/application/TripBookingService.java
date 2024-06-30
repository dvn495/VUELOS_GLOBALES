package com.vuelos_globales.entities.TripBooking.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.BookingStatus.infrastructure.BookingStatusRepository;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;
import com.vuelos_globales.entities.TripBookingDetails.domain.TripBookingDetails;
import com.vuelos_globales.entities.TripBookingDetails.infrastructure.TripBookingDetailsRepository;
import com.vuelos_globales.entities.Customer.infrastructure.CustomerRepository;
import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;
import com.vuelos_globales.entities.FlightConnection.infrastructure.FlightConnectionRepository;
import com.vuelos_globales.entities.FlightFares.domain.FlightFares;
import com.vuelos_globales.entities.FlightFares.infrastructure.FlightFaresRepository;

public class TripBookingService {
    private final TripRepository tripRepository;
    private final CustomerRepository customerRepository;
    private final TripBookingRepository tripBookingRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final FlightFaresRepository flightFaresRepository;
    private final TripBookingDetailsRepository tripBookingDetailsRepository;
    private final FlightConnectionRepository flightConnectionRepository;
    
    public TripBookingService(TripBookingRepository tripBookingRepository, CustomerRepository customerRepository, TripRepository tripRepository, BookingStatusRepository bookingStatusRepository, FlightFaresRepository flightFaresRepository, TripBookingDetailsRepository tripBookingDetailsRepository, FlightConnectionRepository flightConnectionRepository) {
        this.tripBookingRepository = tripBookingRepository;
        this.tripRepository = tripRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.customerRepository = customerRepository;
        this.flightFaresRepository = flightFaresRepository;
        this.tripBookingDetailsRepository = tripBookingDetailsRepository;
        this.flightConnectionRepository = flightConnectionRepository;
        
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

    // FlightConnection

    public Optional<FlightConnection> getFlightConnectionById(String id) {
        return flightConnectionRepository.findById(id);
    }

    public List<FlightConnection> getAllFlightConnections() {
        return flightConnectionRepository.findAll();
    }

    public Optional<FlightConnection> getFlightCOnnectionByTrip(String id){
        return flightConnectionRepository.findByTrip(id);
    }

    //Trip Booking Detailss

    public void createTripBookingDetails(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.save(tripBookingDetails);
    }

    public Optional<TripBookingDetails> getTripBookingDetailsById(String id) {
        return tripBookingDetailsRepository.findById(id);
    }

    public List<TripBookingDetails> getAllTripBookingDetails() {
        return tripBookingDetailsRepository.findAll();
    }

    // Flight Fares

    public void createFlighFareId(FlightFares flightFares) {
        flightFaresRepository.save(flightFares);
    }

    public Optional<FlightFares> getFlightFaresById(String id) {
        return flightFaresRepository.findById(id);
    }

    public List<FlightFares> getAllFlightFares() {
        return flightFaresRepository.findAll();
    }

    //  Customer

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
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

    public List<BookingStatus> getAllBookingStatuses() {
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