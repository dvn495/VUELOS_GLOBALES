package com.vuelos_globales.Gates.infrastructure;

import com.vuelos_globales.Gates.domain.Gates;

import java.util.Optional;
import java.util.List;

public interface GatesRepository {
    void save(Gates gate);
    void update(Gates gate);
    Optional<Gates> findById(String id);
    void delete(String id);
    List<Gates> findAll();
}
