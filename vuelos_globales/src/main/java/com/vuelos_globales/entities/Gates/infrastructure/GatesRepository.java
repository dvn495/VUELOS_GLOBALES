package com.vuelos_globales.entities.Gates.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Gates.domain.Gates;

public interface GatesRepository {
    void save(Gates gate);
    void update(Gates gate);
    Optional<Gates> findById(String id);
    void delete(String id);
    List<Gates> findAll();
}
