package com.vuelos_globales.entities.PlaneModels.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.PlaneModels.domain.PlaneModels;
import com.vuelos_globales.entities.PlaneModels.infrastructure.PlaneModelsRepository;

public class PlaneModelsMySQLRepository implements PlaneModelsRepository{
    private final String url;
    private final String user;
    private final String password;

    public PlaneModelsMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(PlaneModels planeModels){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO model (id, model, idManufacturer) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, planeModels.getId());
                statement.setString(2, planeModels.getModel());
                statement.setString(3, planeModels.getIdManufacturer());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlaneModels planeModels){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE model SET model = ?, idManufacturer = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, planeModels.getModel());
                statement.setString(2, planeModels.getIdManufacturer());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PlaneModels> findById(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM model WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        PlaneModels PlaneModels = new PlaneModels(
                            resultSet.getString("id"),
                            resultSet.getString("manufacturer"),
                            resultSet.getString("idManufacturer")
                        );
                        return Optional.of(PlaneModels);
                    }

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override 
    public void delete(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM model WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<PlaneModels> findAll(){
        List<PlaneModels> PlaneModels = new ArrayList<>();
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM model";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()){
                        PlaneModels planeModel = new PlaneModels(
                            resultSet.getString("id"),
                            resultSet.getString("model"),
                            resultSet.getString("idManufacturer")
                        );
                        PlaneModels.add(planeModel);
                    }
                }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return PlaneModels;
    }
}
