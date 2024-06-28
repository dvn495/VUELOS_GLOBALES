package com.vuelos_globales.entities.TripBookingDetails.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.Customer.infrastructure.CustomerRepository;
import com.vuelos_globales.entities.FlightFares.domain.FlightFares;
import com.vuelos_globales.entities.FlightFares.infrastructure.FlightFaresRepository;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;
import com.vuelos_globales.entities.TripBookingDetails.domain.TripBookingDetails;
import com.vuelos_globales.entities.TripBookingDetails.infrastructure.TripBookingDetailsRepository;

public class TripBookingDetailsService {
    private final TripBookingDetailsRepository tripBookingDetailsRepository;
    private final TripRepository tripRepository;
    private final CustomerRepository customerRepository;
    private final FlightFaresRepository flightFaresRepository;

    public TripBookingDetailsService(TripBookingDetailsRepository tripBookingDetailsRepository, TripRepository tripRepository, CustomerRepository customerRepository, FlightFaresRepository flightFaresRepository){
        this.tripBookingDetailsRepository = tripBookingDetailsRepository;
        this.tripRepository = tripRepository;
        this.customerRepository = customerRepository;
        this.flightFaresRepository = flightFaresRepository;
        
    }

    public void createTripBookingDetails(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.save(tripBookingDetails);
    }

    public void updateTripBookingDetails(TripBookingDetails tripBookingDetails){
        tripBookingDetailsRepository.update(tripBookingDetails);
    }

    public Optional<TripBookingDetails> getTripBookingDetailsById(String id){
        return tripBookingDetailsRepository.findById(id);
    }

    // CUSTOMER

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    // FLIGHT FARES

    public void createFlightFares(FlightFares flightFares) {
        flightFaresRepository.save(flightFares);
    }

    public Optional<FlightFares> getFlightFaresById(String id) {
        return flightFaresRepository.findById(id);
    }

    public List<FlightFares> getAllFlightFares() {
        return flightFaresRepository.findAll();
    }


    // TRIP BOOKING


    public void createTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public Optional<Trip> getTripById(String id) {
        return tripRepository.findById(id);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    //

    public void deleteTripBookingDetails(String id){
        tripBookingDetailsRepository.delete(id);
    }

    public List<TripBookingDetails> getAllTripBookingDetails(){
        return tripBookingDetailsRepository.findAll();
    }
}
