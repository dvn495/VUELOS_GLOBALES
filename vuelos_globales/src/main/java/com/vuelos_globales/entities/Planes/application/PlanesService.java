package com.vuelos_globales.entities.Planes.application;

import java.util.Optional;

import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Planes.infrastructure.PlanesRepository;

import java.util.List;

public class PlanesService {
    private final PlanesRepository planesRepository;

    public PlanesService(PlanesRepository planesRepository){
        this.planesRepository = planesRepository;
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
}

