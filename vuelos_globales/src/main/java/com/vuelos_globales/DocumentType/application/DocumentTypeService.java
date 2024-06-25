package com.vuelos_globales.DocumentType.application;

import java.util.Optional;

import java.util.List;

import com.vuelos_globales.DocumentType.infrastructure.DocumentTypeRepository;
import com.vuelos_globales.DocumentType.domain.DocumentType;

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
