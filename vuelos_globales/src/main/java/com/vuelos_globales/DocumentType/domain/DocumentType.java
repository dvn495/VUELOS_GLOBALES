package com.vuelos_globales.DocumentType.domain;

public class DocumentType {
    private int id;
    private String documentType;
    
    public DocumentType() {}

    public DocumentType(int id, String documentType) {
        this.id = id;
        this.documentType = documentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    
    
}
