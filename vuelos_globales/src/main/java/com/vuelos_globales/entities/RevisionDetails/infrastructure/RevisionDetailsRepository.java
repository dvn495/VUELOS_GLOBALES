package com.vuelos_globales.entities.RevisionDetails.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.RevisionDetails.domain.RevisionDetails;

public interface RevisionDetailsRepository {
    void save(RevisionDetails revisionDetails);
    void update(RevisionDetails revisionDetails);
    Optional<RevisionDetails> findById(String id);
    void delete(String id);
    List<RevisionDetails> findAll();
}
