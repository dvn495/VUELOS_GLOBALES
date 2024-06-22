package com.vuelos_globales.Airlines.application;


import java.util.Optional;
import java.util.List;

import com.vuelos_globales.Airlines.domain.Airlines;
import com.vuelos_globales.Airlines.infrastructure.AirlinesRepository;

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
