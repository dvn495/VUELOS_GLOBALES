package com.vuelos_globales.Gates.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Gates.domain.Gates;
import com.vuelos_globales.Gates.infrastructure.GatesRepository;

public class GatesService {
    private final GatesRepository gatesRepository;

    public GatesService(GatesRepository gatesRepository){
        this.gatesRepository = gatesRepository;
    }

    public void createGate(Gates gates){
        gatesRepository.save(gates);
    }

    public void updateGate(Gates gates){
        gatesRepository.update(gates);
    }

    public Optional<Gates> getGateById(String id){
        return gatesRepository.findById(id);
    }

    public void deleteGates(String id){
        gatesRepository.delete(id);
    }

    public List<Gates> getAllGates(){
        return gatesRepository.findAll();
    }
}
