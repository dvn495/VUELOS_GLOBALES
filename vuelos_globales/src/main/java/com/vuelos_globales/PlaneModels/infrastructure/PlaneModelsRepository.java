package com.vuelos_globales.PlaneModels.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.PlaneModels.domain.PlaneModels;

public interface PlaneModelsRepository {
    void save(PlaneModels models);
    void update(PlaneModels models);
    Optional<PlaneModels> findById(String id);
    void delete(String id);
    List<PlaneModels> findAll();
}
