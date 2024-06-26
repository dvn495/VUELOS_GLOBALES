package com.vuelos_globales.entities.Statuses.adapters.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Statuses.domain.Status;
import com.vuelos_globales.entities.Statuses.infrastructure.StatusRepository;

public class StatusMySQLRepository implements StatusRepository{
    private final String url;
    private final String user;
    private final String password;

    public StatusMySQLRepository(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Status status){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO status (id, status) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, status.getId());
                statement.setString(2, status.getStatus());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Status status){
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE status SET status = ? WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, status.getStatus());
                statement.setString(2, status.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override 
    public Optional<Status> findById(String id){
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, status FROM status WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if(resultSet.next()){
                        Status status = new Status(
                            resultSet.getString("id"),
                            resultSet.getString("status")
                        );
                        return Optional.of(status);
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override public void delete(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM status WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Status> findAll(){
        List<Status> statuses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, status FROM status";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Status status = new Status(
                        resultSet.getString("id"),
                        resultSet.getString("status")
                    );
                    statuses.add(status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }
}