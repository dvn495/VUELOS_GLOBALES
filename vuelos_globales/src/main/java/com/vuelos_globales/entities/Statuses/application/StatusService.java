package com.vuelos_globales.entities.Statuses.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Statuses.domain.Status;
import com.vuelos_globales.entities.Statuses.infrastructure.StatusRepository;

public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }


    public void createStatus(Status status){
        statusRepository.save(status);
    }

    public void updateStatus(Status status){
        statusRepository.update(status);
    }

    public Optional<Status> getStatusById(String id){
        return statusRepository.findById(id);
    }

    public void deleteStatus(String id){
        statusRepository.delete(id);
    }

    public List<Status> getAllStatuses(){
        return statusRepository.findAll();
    }

}