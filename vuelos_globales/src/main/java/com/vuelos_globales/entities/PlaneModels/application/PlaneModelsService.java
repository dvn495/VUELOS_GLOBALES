package com.vuelos_globales.entities.PlaneModels.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.PlaneModels.domain.PlaneModels;
import com.vuelos_globales.entities.PlaneModels.infrastructure.PlaneModelsRepository;

public class PlaneModelsService {
    private final PlaneModelsRepository planeModelsRepository;

    public PlaneModelsService(PlaneModelsRepository planeModelsRepository) {
        this.planeModelsRepository = planeModelsRepository;
    }

    public void createPlaneModels(PlaneModels planeModels){
        planeModelsRepository.save(planeModels);
    }

    public void updatePlaneModels(PlaneModels planeModels){
        planeModelsRepository.update(planeModels);
    }

    public Optional<PlaneModels> findById(String id){
        return planeModelsRepository.findById(id);
    }

    public void deletePlaneModels(String id){
        planeModelsRepository.delete(id);
    }

    public List<PlaneModels> findAll(){
        return planeModelsRepository.findAll();
    }

}
