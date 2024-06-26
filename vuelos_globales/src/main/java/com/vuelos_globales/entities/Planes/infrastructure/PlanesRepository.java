package com.vuelos_globales.entities.Planes.infrastructure;

import java.util.Optional;

import com.vuelos_globales.entities.Planes.domain.Planes;

import java.util.List;

public interface PlanesRepository {
    void save(Planes planes);
    void update(Planes planes);
    Optional<Planes> findById(String id);
    void delete(String id);
    List<Planes> findAll();
}
