package com.vuelos_globales.entities.Airlines.application;


import java.util.Optional;

import com.vuelos_globales.entities.Airlines.domain.Airlines;
import com.vuelos_globales.entities.Airlines.infrastructure.AirlinesRepository;

import java.util.List;

public class AirlinesService {
    private final AirlinesRepository airlinesRepository;
    
    public AirlinesService(AirlinesRepository airlinesRepository){
        this.airlinesRepository = airlinesRepository;
    }

    public void createAirline(Airlines airline){
        airlinesRepository.save(airline);
    }

    public void updateAirline(Airlines airline){
        airlinesRepository.update(airline);
    }

    public Optional<Airlines> getAirlineById(String id){
        return airlinesRepository.findById(id);
    }

    public void deleteAirline(String id){
        airlinesRepository.delete(id);
    }

    public List<Airlines> getAllAirlines(){
        return airlinesRepository.findAll();
    }
}
