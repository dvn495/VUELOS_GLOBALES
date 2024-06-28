package com.vuelos_globales.entities.TripCrew.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Airlines.domain.Airlines;
import com.vuelos_globales.entities.Airlines.infrastructure.AirlinesRepository;
import com.vuelos_globales.entities.Airport.domain.Airport;
import com.vuelos_globales.entities.Airport.infrastructure.AirportRepository;
import com.vuelos_globales.entities.Employee.domain.Employee;
import com.vuelos_globales.entities.Employee.infrastructure.EmployeeRepository;
import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;
import com.vuelos_globales.entities.FlightConnection.infrastructure.FlightConnectionRepository;
import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Planes.infrastructure.PlanesRepository;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.Trip.infrastructure.TripRepository;
import com.vuelos_globales.entities.TripCrew.domain.TripCrew;
import com.vuelos_globales.entities.TripCrew.infrastructure.TripCrewRepository;
import com.vuelos_globales.entities.TripulationRoles.domain.TripulationRole;
import com.vuelos_globales.entities.TripulationRoles.infrastructure.TripulationRoleRepository;

public class TripCrewService {
    private final EmployeeRepository employeeRepository;
    private final FlightConnectionRepository flightConnectionRepository;
    private final TripCrewRepository tripCrewRepository;
    private final TripulationRoleRepository tripulationRoleRepository;
    private final AirlinesRepository airlinesRepository;
    private final AirportRepository airportRepository;
    private final PlanesRepository planesRepository;
    private final TripRepository tripRepository;


    public TripCrewService(TripCrewRepository tripCrewRepository, EmployeeRepository employeeRepository, FlightConnectionRepository flightConnectionRepository,
     TripulationRoleRepository tripulationRoleRepository, AirlinesRepository airlinesRepository,AirportRepository airportRepository, PlanesRepository planesRepository,
     TripRepository tripRepository){
        this.tripCrewRepository = tripCrewRepository;
        this.employeeRepository = employeeRepository;
        this.flightConnectionRepository = flightConnectionRepository;
        this.tripulationRoleRepository = tripulationRoleRepository;
        this.airlinesRepository = airlinesRepository;
        this.airportRepository = airportRepository;
        this.planesRepository = planesRepository;
        this.tripRepository = tripRepository;

    }

    public void createTripCrew(TripCrew tripCrew){
        tripCrewRepository.save(tripCrew);
    }

    public void updateTripCrew(TripCrew tripCrew){
        tripCrewRepository.update(tripCrew);
    }

    public Optional<TripCrew> getTripCrewById(String id){
        return tripCrewRepository.findById(id);

    }

    public void deleteTripCrew(String id){
        tripCrewRepository.delete(id);
    }

    public List<TripCrew> getAllTripCrews(){
        return tripCrewRepository.findAll();
    }

    public Optional<TripCrew> getTripCrewByIdTrip(String id){
        return tripCrewRepository.findByIdTrip(id);

    }

    // FLIGHT CONNECTION
    public void createFlightConnection(FlightConnection flightConnection) {
        flightConnectionRepository.save(flightConnection);
    }

    public Optional<FlightConnection> getFlightConnectionById(String id) {
        return flightConnectionRepository.findById(id);
    }

    public Optional<FlightConnection> getFlightConnectionByIdTrip(String idTrip) {
        return flightConnectionRepository.findById(idTrip);
    }

    public List<FlightConnection> getAllFlightConnections() {
        return flightConnectionRepository.findAll();
    }

    //EMPLOYEE
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // TRIPULATION ROLES

    public List<TripulationRole> getAllTripulationRoles(){
        return tripulationRoleRepository.findAll();
    }

    // AIRPORT

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    //AIRLINE

    public List<Airlines> getAllAirlines(){
        return airlinesRepository.findAll();
    }

    //PLANES

    public List<Planes> findAllPlanes(){
        return planesRepository.findAll();
    }

    //TRIPS

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

}
