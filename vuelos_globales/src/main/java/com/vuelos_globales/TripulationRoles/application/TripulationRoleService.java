package com.vuelos_globales.TripulationRoles.application;

import com.vuelos_globales.TripulationRoles.domain.TripulationRole;
import com.vuelos_globales.TripulationRoles.infrastructure.TripulationRoleRepository;
import java.util.List;
import java.util.Optional;

public class TripulationRoleService {
    private final TripulationRoleRepository tripulationRoleRepository;

    public TripulationRoleService(TripulationRoleRepository tripulationRoleRepository){
        this.tripulationRoleRepository = tripulationRoleRepository;
    }

    public void createTripulationRole(TripulationRole tripulationRole){
        tripulationRoleRepository.save(tripulationRole);
    }

    public void updateTripulationRole(TripulationRole tripulationRole){
        tripulationRoleRepository.update(tripulationRole);    
    }

    public Optional<TripulationRole> getTripulationRoleById(String id) {
        return tripulationRoleRepository.findById(id);
    }

    public void deleteTripulationRole(String id){
    tripulationRoleRepository.delete(id);
    }

    public List<TripulationRole> getAllTripulationRoles(){
        return tripulationRoleRepository.findAll();
    }

}
