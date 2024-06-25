package com.vuelos_globales.Airlines.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Airlines.domain.Airlines;
import com.vuelos_globales.Airlines.infrastructure.AirlinesRepository;


public class AirlinesMySQLRepository implements AirlinesRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirlinesMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Airlines airline) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO airline (id, airline) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airline.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airlines airline) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE airline SET aerolinea = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airline.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Airlines> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airline WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Airlines airline = new Airlines(
                            resultSet.getString("id"),
                            resultSet.getString("nombre")
                        );
                        return Optional.of(airline);
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
            String query = "DELETE FROM airline WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airlines> findAll() {
        List<Airlines> airlines = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airline";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Airlines airline = new Airlines(
                        resultSet.getString("id"),
                        resultSet.getString("nombre")
                    );
                    airlines.add(airline);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlines;
    }
}
