package com.vuelos_globales.Planes.application;

import java.util.Optional;
import java.util.List;

import com.vuelos_globales.Planes.domain.Planes;
import com.vuelos_globales.Planes.infrastructure.PlanesRepository;

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

