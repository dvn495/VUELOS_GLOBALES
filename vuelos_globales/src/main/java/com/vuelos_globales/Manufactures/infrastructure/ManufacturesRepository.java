package com.vuelos_globales.Manufactures.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Manufactures.domain.Manufactures;
public interface ManufacturesRepository {
    void save(Manufactures manufacture);
    void update(Manufactures manufacture);
    Optional<Manufactures> findById(String id);
    void delete(String id);
    List<Manufactures> findAll();
}
