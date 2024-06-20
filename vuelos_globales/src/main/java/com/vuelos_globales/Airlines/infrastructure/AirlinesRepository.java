package com.vuelos_globales.Airlines.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Airlines.domain.Airlines;

public interface AirlinesRepository {
    void save(Airlines airlines);
    void update(Airlines airlines);
    Optional<Airlines> findById(int id);
    void delete(int id);
    List<Airlines> findAll();
}
