package com.vuelos_globales.entities.TripCrew.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripCrew.domain.TripCrew;
import com.vuelos_globales.entities.TripCrew.infrastructure.TripCrewRepository;


public class TripCrewMySQLRepository implements TripCrewRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripCrewMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    
    @Override
    public void save(TripCrew tripCrew) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_crews (id, idEmployee, idConnection) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripCrew.getId());
                statement.setString(2, tripCrew.getIdEmployee());
                statement.setString(3, tripCrew.getIdConnection());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripCrew tripCrew) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip_crews SET idEmployee = ?, idConnection = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripCrew.getIdEmployee());
                statement.setString(2, tripCrew.getIdConnection());
                statement.setString(3, tripCrew.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripCrew> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, idEmployee, idConnection FROM trip_crews WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripCrew tripCrew = new TripCrew(
                            resultSet.getString("id"),
                            resultSet.getString("idEmployee"),
                            resultSet.getString("idConnection")
                        );
                        return Optional.of(tripCrew);
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
            String query = "DELETE FROM trip_crews WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripCrew> findAll() {
        List<TripCrew> tripCrews = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, idEmployee, idConnection FROM trip_crews";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TripCrew tripCrew = new TripCrew(
                        resultSet.getString("id"),
                        resultSet.getString("idEmployee"),
                        resultSet.getString("idConnection")
                    );
                    tripCrews.add(tripCrew);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripCrews;
    }

    @Override
    public Optional<TripCrew> findByIdTrip(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT tc.idEmployee FROM trip_crews as tc JOIN flight_connection as f ON tc.idConnection = f.id JOIN trip as t ON f.idTrip = t.id WHERE t.id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        TripCrew tripCrew = new TripCrew(
                            resultSet.getString("id"),
                            resultSet.getString("idEmployee"),
                            resultSet.getString("idConnection")
                        );
                        return Optional.of(tripCrew);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}