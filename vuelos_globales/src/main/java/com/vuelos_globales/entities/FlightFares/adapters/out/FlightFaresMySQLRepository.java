package com.vuelos_globales.entities.FlightFares.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.FlightFares.domain.FlightFares;
import com.vuelos_globales.entities.FlightFares.infrastructure.FlightFaresRepository;

public class FlightFaresMySQLRepository implements FlightFaresRepository {
    private final String url;
    private final String user;
    private final String password;

    public FlightFaresMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(FlightFares flightfare){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO flight_fares (id, description, details, value) VALUES (?, ?, ? , ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, flightfare.getId());
                statement.setString(2, flightfare.getDescription());
                statement.setString(3, flightfare.getDetails());
                statement.setDouble(4, flightfare.getValue());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(FlightFares flightFares){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE flight_fares SET description = ?, details = ? , value = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, flightFares.getDescription());
                statement.setString(2, flightFares.getDetails());
                statement.setDouble(3, flightFares.getValue());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FlightFares> findById(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM flight_fares WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        FlightFares flightfare = new FlightFares(
                            resultSet.getString("id"),
                            resultSet.getString("description"),
                            resultSet.getString("details"),
                            resultSet.getDouble("value")
                        );
                        return Optional.of(flightfare);
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
            try (Statement disableFK = connection.createStatement()) {
                disableFK.execute("SET FOREIGN_KEY_CHECKS = 0");
            }
            
            String query = "DELETE FROM flight_fares WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }

            try (Statement enableFK = connection.createStatement()) {
                enableFK.execute("SET FOREIGN_KEY_CHECKS = 1");
            }   
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<FlightFares> findAll(){
        List<FlightFares> flightFares = new ArrayList<>();
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM flight_fares";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()){
                        FlightFares flightFare = new FlightFares(
                            resultSet.getString("id"),
                            resultSet.getString("description"),
                            resultSet.getString("details"),
                            resultSet.getDouble("value")
                        );
                        flightFares.add(flightFare);
                    }
                }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flightFares;
    }

    

    
}
