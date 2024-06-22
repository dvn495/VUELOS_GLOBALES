package com.vuelos_globales.Airlines.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Airlines.domain.Airlines;

public interface AirlinesRepository {
    void save(Airlines airlines);
    void update(Airlines airlines);
    Optional<Airlines> findById(String id);
    void delete(String id);
    List<Airlines> findAll();
}
