package com.vuelos_globales.Revisions.infrastructure;

import java.util.List;
import java.util.Optional;
import com.vuelos_globales.Revisions.domain.Revisions;

public interface RevisionsRepository {
    void save(Revisions revision);
    void update(Revisions revision);
    Optional<Revisions> findById(String id);
    void delete(String id);
    List<Revisions> findAll();
}
