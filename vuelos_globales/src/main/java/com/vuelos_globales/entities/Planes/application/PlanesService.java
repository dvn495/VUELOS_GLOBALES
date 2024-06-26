package com.vuelos_globales.entities.Planes.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.PlaneModels.domain.PlaneModels;
import com.vuelos_globales.entities.PlaneModels.infrastructure.PlaneModelsRepository;
import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Planes.infrastructure.PlanesRepository;

public class PlanesService {
    
    private final PlanesRepository planesRepository;
    private final PlaneModelsRepository planeModelsRepository;

    public PlanesService(PlanesRepository planesRepository, PlaneModelsRepository planeModelsRepository){
        this.planesRepository = planesRepository;
        this.planeModelsRepository = planeModelsRepository; 
    }

    public void createPlanes(Planes planes){
        planesRepository.save(planes);
    }

    public void updatePlanes(Planes planes){
        planesRepository.update(planes);
    }

    public Optional<Planes> findById(String id){
        return planesRepository.findById(id);
    }

    public void deletePlanes(String id){
        planesRepository.delete(id);
    }

    public List<Planes> findAll(){
        return planesRepository.findAll();
    }

    public void createPlaneModels(PlaneModels planeModels){
        planeModelsRepository.save(planeModels);
    }

    public List<PlaneModels> findAllModels(){
        return planeModelsRepository.findAll();
    }
}

