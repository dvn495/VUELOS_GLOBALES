package com.vuelos_globales.entities.FlightConnection.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;
import com.vuelos_globales.entities.FlightConnection.infrastructure.FlightConnectionRepository;

public class FlightConnectionMySQLRepository implements FlightConnectionRepository {
    private final String url;
    private final String user;
    private final String password;

    public FlightConnectionMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(FlightConnection flightConnection) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO flight_connection (id, connectionOrder, idTrip, idPlane, idAirportA, idAirportB) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightConnection.getId());
                statement.setInt(2, flightConnection.getConnectionOrder());
                statement.setString(3, flightConnection.getIdTrip());
                statement.setString(4, flightConnection.getIdPlane());
                statement.setString(5, flightConnection.getIdAirportA());
                statement.setString(6, flightConnection.getIdArportB());
                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(FlightConnection flightConnection) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE flight_connection SET id, connectionOrder, idTrip, idPlane, idAirportA, idAirportB WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightConnection.getId());
                statement.setInt(2, flightConnection.getConnectionOrder());
                statement.setString(3, flightConnection.getIdTrip());
                statement.setString(4, flightConnection.getIdPlane());
                statement.setString(5, flightConnection.getIdAirportA());
                statement.setString(6, flightConnection.getIdArportB());
                statement.executeUpdate();
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FlightConnection> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, connectionOrder, idTrip, idPlane, idAirportA, idAirportB  FROM flight_connection WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        FlightConnection flightConnection = new FlightConnection(
                            resultSet.getString("id"),
                            resultSet.getInt("connectionOrder"),
                            resultSet.getString("idTrip"),
                            resultSet.getString("idPlane"),
                            resultSet.getString("idAirportA"),
                            resultSet.getString("idAirportB")
                        );
                        return Optional.of(flightConnection);
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
            try (Statement disableFK = connection.createStatement()) {
                disableFK.execute("SET FOREIGN_KEY_CHECKS = 0");
            }

            String query = "DELETE FROM flight_connection WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
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
    public List<FlightConnection> findAll() {
        List<FlightConnection> flightConnections = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, connectionOrder, idTrip, idPlane, idAirportA, idAirportB FROM flight_connection";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    FlightConnection flightConnection = new FlightConnection(
                        resultSet.getString("id"),
                        resultSet.getInt("connectionOrder"),
                        resultSet.getString("idTrip"),
                        resultSet.getString("idPlane"),
                        resultSet.getString("idAirportA"),
                        resultSet.getString("idAirportB")
                    );
                    flightConnections.add(flightConnection);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightConnections;
    }

    @Override
    public Optional<FlightConnection> findByTrip(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, connectionOrder, idTrip, idPlane, idAirportA, idAirportB FROM flight_connection WHERE idTrip = ?  ORDER BY connectionOrder";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        FlightConnection flightConnection = new FlightConnection(
                            resultSet.getString("id"),
                            resultSet.getInt("connectionOrder"),
                            resultSet.getString("idTrip"),
                            resultSet.getString("idPlane"),
                            resultSet.getString("idAirportA"),
                            resultSet.getString("idAirportB")
                        );
                        return Optional.of(flightConnection);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    

}
