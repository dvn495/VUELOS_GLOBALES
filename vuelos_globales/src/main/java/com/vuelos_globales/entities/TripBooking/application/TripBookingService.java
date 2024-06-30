package com.vuelos_globales.entities.TripBooking.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.BookingStatus.infrastructure.BookingStatusRepository;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;
import com.vuelos_globales.entities.Customer.infrastructure.CustomerRepository;
import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Planes.infrastructure.PlanesRepository;

public class TripBookingService {
    private final TripRepository tripRepository;
    private final TripBookingRepository tripBookingRepository;
    private final BookingStatusRepository bookingStatusRepository;
    private final CustomerRepository customerRepository;
    private final PlanesRepository planesRepository;
    
    public TripBookingService(TripBookingRepository tripBookingRepository, TripRepository tripRepository, BookingStatusRepository bookingStatusRepository, CustomerRepository customerRepository, PlanesRepository planesRepository) {
        this.tripBookingRepository = tripBookingRepository;
        this.tripRepository = tripRepository;
        this.bookingStatusRepository = bookingStatusRepository;
        this.customerRepository = customerRepository;
        this.planesRepository = planesRepository;
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

    // CLIENTES

    public void createClient(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    // AVIONES

    public List<Planes> getAllAirplanes() {
        return planesRepository.findAll();
    }

    public Optional<Planes> getPlaneById(String id) {
        return planesRepository.findById(id);
    }

    public int getCapacity(String id) {
        return planesRepository.getMaxCapacity(id);
    }
    
    // BOOKING STATUS

    public List<String> getAllBookingStatuses() {
        return tripBookingRepository.findAllBookingTypes();
    }

}