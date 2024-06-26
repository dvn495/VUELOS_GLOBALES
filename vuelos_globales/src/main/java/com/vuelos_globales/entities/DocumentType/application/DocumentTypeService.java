package com.vuelos_globales.entities.DocumentType.application;

import java.util.Optional;

import com.vuelos_globales.entities.DocumentType.domain.DocumentType;
import com.vuelos_globales.entities.DocumentType.infrastructure.DocumentTypeRepository;

import java.util.List;

public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public void createDocumentType(DocumentType documentType) {
        documentTypeRepository.save(documentType);
    }

    public void updateDocumentType(DocumentType documentType) {
        documentTypeRepository.update(documentType);
    }

    public Optional<DocumentType> getDocumentTypeById(int id) {
        return documentTypeRepository.findById(id);
    }

    public void deleteDocumentType(int id) {
        documentTypeRepository.delete(id);
    }

    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    } 


}
