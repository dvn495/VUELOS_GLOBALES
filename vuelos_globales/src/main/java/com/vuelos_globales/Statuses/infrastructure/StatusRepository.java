package com.vuelos_globales.Statuses.infrastructure;

import com.vuelos_globales.Statuses.domain.Status;
import java.util.List;
import java.util.Optional;

public interface StatusRepository {
    void save(Status status);
    void update(Status status);
    Optional<Status> findById(String id);
    void delete(String id);
    List<Status> findAll();
}
