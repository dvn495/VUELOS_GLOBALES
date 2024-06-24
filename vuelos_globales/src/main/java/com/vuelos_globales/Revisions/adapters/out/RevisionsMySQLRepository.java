package com.vuelos_globales.Revisions.adapters.out;

import com.vuelos_globales.Revisions.domain.Revisions;
import com.vuelos_globales.Revisions.infrastructure.RevisionsRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RevisionsMySQLRepository implements RevisionsRepository{
    private final String url;
    private final String user;
    private final String password;


    public RevisionsMySQLRepository(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Revisions revision){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO revision (id, revisionDate, idPlane, idDetails) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, revision.getId());
                statement.setDate(2, revision.getRevisionDate());
                statement.setString(3, revision.getIdPlane());
                statement.setString(4, revision.getIdDetails());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Revisions revision){
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE revision SET revisionDate = ?, idPlane = ?, idDetails = ? WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setDate(1, revision.getRevisionDate());
                statement.setString(2, revision.getIdPlane());
                statement.setString(3, revision.getIdDetails());
                statement.setString(4, revision.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Revisions> findById(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM revision WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        Revisions revision = new Revisions(
                            resultSet.getString("id"),
                            resultSet.getDate("revisionDate"),
                            resultSet.getString("idPlane"),
                            resultSet.getString("idDetails")
                        );
                        return Optional.of(revision);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }  


    @Override public void delete(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM revision WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Revisions> findAll() {
        List<Revisions> revisions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, revisionDate, idPlane, idDetails FROM revision";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Revisions revision = new Revisions(
                        resultSet.getString("id"),
                        resultSet.getDate("revisionDate"),
                        resultSet.getString("idPlane"),
                        resultSet.getString("idDetails")
                    );
                    revisions.add(revision);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revisions;
    }
}

