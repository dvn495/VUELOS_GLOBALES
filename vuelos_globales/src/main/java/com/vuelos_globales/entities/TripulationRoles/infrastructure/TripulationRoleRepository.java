package com.vuelos_globales.entities.TripulationRoles.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripulationRoles.domain.TripulationRole;

public interface TripulationRoleRepository {
    void save(TripulationRole tripulationRole);
    void update(TripulationRole tripulationRole);
    Optional <TripulationRole> findById(String id);
    void delete(String id);
    List<TripulationRole> findAll();
}
