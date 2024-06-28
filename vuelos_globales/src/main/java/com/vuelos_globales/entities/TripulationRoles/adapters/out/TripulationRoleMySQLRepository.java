package com.vuelos_globales.entities.TripulationRoles.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripulationRoles.domain.TripulationRole;
import com.vuelos_globales.entities.TripulationRoles.infrastructure.TripulationRoleRepository;

public class TripulationRoleMySQLRepository implements TripulationRoleRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripulationRoleMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripulationRole tripulationRole){
            try(Connection connection = DriverManager.getConnection(url, user, password)){
                String query = "INSERT INTO tripulation_role (id, roleName) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setString(1, tripulationRole.getId());
                    statement.setString(2, tripulationRole.getRole());
                    statement.executeUpdate();
                }
            } catch (SQLException e){
                e.printStackTrace();
        } 
    }


    @Override
    public void update(TripulationRole tripulationRole) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripulation_role SET roleName = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripulationRole.getRole());
                statement.setString(2, tripulationRole.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public Optional<TripulationRole> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, roleName FROM tripulation_role WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripulationRole tripulationRole = new TripulationRole(
                            resultSet.getString("id"),
                            resultSet.getString("roleName")
                        );
                        return Optional.of(tripulationRole);
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    }

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM tripulation_role WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripulationRole> findAll() {
        List<TripulationRole> tripulationRoles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, roleName FROM tripulation_role";
            try (PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripulationRole tripulationRole = new TripulationRole(
                        resultSet.getString("id"),
                        resultSet.getString("roleName")
                    );
                    tripulationRoles.add(tripulationRole);
                }
            } 
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return tripulationRoles;
    }

}