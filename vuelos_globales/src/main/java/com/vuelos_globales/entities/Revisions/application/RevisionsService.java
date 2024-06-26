
package com.vuelos_globales.entities.Revisions.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Revisions.domain.Revisions;
import com.vuelos_globales.entities.Revisions.infrastructure.RevisionsRepository;

public class RevisionsService {
    private final RevisionsRepository revisionsRepository;

    public RevisionsService(RevisionsRepository revisionsRepository){
        this.revisionsRepository = revisionsRepository;
    }

    public void createRevision(Revisions revision){
        revisionsRepository.save(revision);

    }

    public void updateRevisions(Revisions revision){
        revisionsRepository.update(revision);

    }

    public Optional <Revisions> getRevisionById(String id){
        return revisionsRepository.findById(id);
    }

    public void deleteRevision(String id) {
        revisionsRepository.delete(id);
    }

    public List<Revisions> getAllRevisions() {
        return revisionsRepository.findAll();
    }

}
