package com.vuelos_globales.entities.Planes.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Planes.infrastructure.PlanesRepository;

public class PlanesMySQLRepository implements PlanesRepository{
    private final String url;
    private final String user;
    private final String password;

    public PlanesMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Planes planes){
        java.sql.Date sqlDate = java.sql.Date.valueOf(planes.getFabricationDate());
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO plane (id, plates, capacity, fabricationDate, idModel, idStatus ) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, planes.getId());
                statement.setString(2, planes.getPlates());
                statement.setInt(3, planes.getCapacity());
                statement.setDate(4, sqlDate);
                statement.setString(5, planes.getIdModel());
                statement.setString(6, planes.getIdStatus());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Planes planes){
        java.sql.Date sqlDate = java.sql.Date.valueOf(planes.getFabricationDate());
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            try (Statement disableFK = connection.createStatement()) {
                disableFK.execute("SET FOREIGN_KEY_CHECKS = 0");
            }

            String query = "UPDATE plane SET plates = ?,capacity = ?, fabricationDate = ?, idModel = ?, idStatus = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, planes.getPlates());
                statement.setInt(2, planes.getCapacity());
                statement.setDate(3, sqlDate);
                statement.setString(4, planes.getIdModel());
                statement.setString(5, planes.getIdStatus());
                statement.setString(6, planes.getId());
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
    public Optional<Planes> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM plane WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        java.sql.Date sqlDate = resultSet.getDate("fabricationDate");
                        LocalDate fabricationDate = sqlDate.toLocalDate();
                        Planes plane = new Planes(
                            resultSet.getString("id"),
                            resultSet.getString("plates"),
                            resultSet.getInt("capacity"),
                            fabricationDate, 
                            resultSet.getString("idModel"),
                            resultSet.getString("idStatus")
                        );
                        return Optional.of(plane);
                    }
                }
            }
        } catch (SQLException e) {
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

            String query = "DELETE FROM plane WHERE id = ?";
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
    public List<Planes> findAll(){
        List<Planes> Planes = new ArrayList<>();
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM plane";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()){
                        java.sql.Date sqlDate = resultSet.getDate("fabricationDate");
                        LocalDate fabricationDate = sqlDate.toLocalDate();
                        Planes plane = new Planes(
                            resultSet.getString("id"),
                            resultSet.getString("plates"),
                            resultSet.getInt("capacity"),
                            fabricationDate, 
                            resultSet.getString("idModel"),
                            resultSet.getString("idStatus")
                        );
                        Planes.add(plane);
                    }
                }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Planes;
    }

    @Override
    public int getMaxCapacity(String id) {
        int maxCapacity = 0;
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT capacity FROM plane WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    maxCapacity = resultSet.getInt("capacity");
                }
            }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return maxCapacity;
    }
}
