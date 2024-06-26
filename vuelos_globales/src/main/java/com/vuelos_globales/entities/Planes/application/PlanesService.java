package com.vuelos_globales.entities.Planes.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Manufactures.domain.Manufactures;
import com.vuelos_globales.entities.Manufactures.infrastructure.ManufacturesRepository;
import com.vuelos_globales.entities.PlaneModels.domain.PlaneModels;
import com.vuelos_globales.entities.PlaneModels.infrastructure.PlaneModelsRepository;
import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Planes.infrastructure.PlanesRepository;
import com.vuelos_globales.entities.Statuses.domain.Status;
import com.vuelos_globales.entities.Statuses.infrastructure.StatusRepository;

public class PlanesService {
    private final StatusRepository statusRepository;
    private final PlanesRepository planesRepository;
    private final ManufacturesRepository manufacturesRepository;
    private final PlaneModelsRepository planeModelsRepository;

    public PlanesService(PlanesRepository planesRepository, PlaneModelsRepository planeModelsRepository, StatusRepository statusRepository, ManufacturesRepository manufacturesRepository){
        this.planesRepository = planesRepository;
        this.planeModelsRepository = planeModelsRepository; 
        this.statusRepository = statusRepository;
        this.manufacturesRepository = manufacturesRepository;
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

    // PLANE MODELS

    public void createPlaneModels(PlaneModels planeModels){
        planeModelsRepository.save(planeModels);
    }

    public List<PlaneModels> findAllModels(){
        return planeModelsRepository.findAll();
    }

    public Optional<PlaneModels> findByIdModel(String id){
        return planeModelsRepository.findById(id);
    }

    // STATUSES

    public void createStatus(Status status){
        statusRepository.save(status);
    }

    public Optional<Status> getStatusById(String id){
        return statusRepository.findById(id);
    }

    public List<Status> getAllStatuses(){
        return statusRepository.findAll();
    }

    // MANUFACTURES

    public void createManufacturer(Manufactures manufacture){
        manufacturesRepository.save(manufacture);
    }

    public Optional<Manufactures> getManufacturerById(String id){
        return manufacturesRepository.findById(id);
    }

    public List<Manufactures> getAllManufactures(){
        return manufacturesRepository.findAll();
    }


}

