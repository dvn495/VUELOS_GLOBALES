package com.vuelos_globales.entities.RevisionDetails.application;

import java.util.Optional;
import java.util.List;

import com.vuelos_globales.entities.RevisionDetails.domain.RevisionDetails;
import com.vuelos_globales.entities.RevisionDetails.infrastructure.RevisionDetailsRepository;

public class RevisionDetailsService {
    private final RevisionDetailsRepository revisionDetailsRepository;

    public RevisionDetailsService(RevisionDetailsRepository revisionDetailsRepository) {
        this.revisionDetailsRepository = revisionDetailsRepository;
    }

    public void createRevisionDetail(RevisionDetails revisionDetails){
        revisionDetailsRepository.save(revisionDetails);
    }

    public void updateRevisionDetail(RevisionDetails revisionDetails){
        revisionDetailsRepository.update(revisionDetails);
    }

    public Optional<RevisionDetails> findById(String id){
        return revisionDetailsRepository.findById(id);
    }

    public void deleteRevisionDetails(String id){
        revisionDetailsRepository.delete(id);
    }

    public List<RevisionDetails> findAll(){
        return revisionDetailsRepository.findAll();
    }
}
