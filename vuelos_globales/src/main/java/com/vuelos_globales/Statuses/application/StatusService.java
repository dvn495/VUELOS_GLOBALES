package com.vuelos_globales.Statuses.application;

import com.vuelos_globales.Statuses.infrastructure.StatusRepository;
import com.vuelos_globales.Statuses.domain.Status;
import java.util.List;
import java.util.Optional;

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