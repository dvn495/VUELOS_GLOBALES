package com.vuelos_globales.entities.DocumentType.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.DocumentType.domain.DocumentType;
import com.vuelos_globales.entities.DocumentType.infrastructure.DocumentTypeRepository;

public class DocTypeMySQLRepository implements DocumentTypeRepository{
    private final String url;
    private final String user;
    private final String password;
    
    public DocTypeMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    @Override
    public void save(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO document_types (id, documentType) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, documentType.getId());
                statement.setString(2, documentType.getDocumentType());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DocumentType documentType) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE document_types SET documentType = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, documentType.getDocumentType());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<DocumentType> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, documentType FROM document_types WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        DocumentType DocumentType = new DocumentType(
                            resultSet.getInt("id"),
                            resultSet.getString("documentType")
                        );
                        return Optional.of(DocumentType);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    }
    
    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement disableFK = connection.createStatement()) {
                disableFK.execute("SET FOREIGN_KEY_CHECKS = 0");
            }
            
            String query = "DELETE FROM document_types WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            
            try (Statement enableFK = connection.createStatement()) {
                enableFK.execute("SET FOREIGN_KEY_CHECKS = 1");
            }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<DocumentType> findAll() {
        List<DocumentType> documentTypes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, documentType FROM document_types";
            try (PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DocumentType documentType = new DocumentType(
                        resultSet.getInt("id"),
                        resultSet.getString("documentType")
                    );
                    documentTypes.add(documentType);
                }
            } 
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return documentTypes;
    }
    
}
