package com.vuelos_globales.Planes.infrastructure;

import java.util.Optional;
import java.util.List;

import com.vuelos_globales.Planes.domain.Planes;

public interface PlanesRepository {
    void save(Planes planes);
    void update(Planes planes);
    Optional<Planes> findById(String id);
    void delete(String id);
    List<Planes> findAll();
}
