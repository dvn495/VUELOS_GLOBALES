package com.vuelos_globales.entities.TripCrew.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Employee.domain.Employee;
import com.vuelos_globales.entities.Employee.infrastructure.EmployeeRepository;
import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;
import com.vuelos_globales.entities.FlightConnection.infrastructure.FlightConnectionRepository;
import com.vuelos_globales.entities.TripCrew.domain.TripCrew;
import com.vuelos_globales.entities.TripCrew.infrastructure.TripCrewRepository;

public class TripCrewService {
    private final EmployeeRepository employeeRepository;
    private final FlightConnectionRepository flightConnectionRepository;
    private final TripCrewRepository tripCrewRepository;


    public TripCrewService(TripCrewRepository tripCrewRepository, EmployeeRepository employeeRepository, FlightConnectionRepository flightConnectionRepository){
        this.tripCrewRepository = tripCrewRepository;
        this.employeeRepository = employeeRepository;
        this.flightConnectionRepository = flightConnectionRepository;
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

    // FLIGHT CONNECTION
    public void createFlightConnection(FlightConnection flightConnection) {
        flightConnectionRepository.save(flightConnection);
    }

    public Optional<FlightConnection> getFlightConnectionById(String id) {
        return flightConnectionRepository.findById(id);
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
}
