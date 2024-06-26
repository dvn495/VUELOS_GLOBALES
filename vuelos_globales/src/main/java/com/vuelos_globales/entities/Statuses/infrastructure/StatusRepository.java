package com.vuelos_globales.entities.Statuses.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Statuses.domain.Status;

public interface StatusRepository {
    void save(Status status);
    void update(Status status);
    Optional<Status> findById(String id);
    void delete(String id);
    List<Status> findAll();
}
