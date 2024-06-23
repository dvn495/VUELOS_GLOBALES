package com.vuelos_globales.City.adapters.out;

import com.vuelos_globales.City.infrastructure.CityRepository;
import com.vuelos_globales.City.domain.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CityMySQLRepository implements CityRepository {
    private final String url;
    private final String user;
    private final String password;
    
    public CityMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(City city) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO city (id, city, idCountry) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getId());
                statement.setString(2, city.getCityName());
                statement.setString(3, city.getCountryCity());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(City city) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE city SET city = ?, idCountry = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, city.getCityName());
                statement.setString(2, city.getCountryCity());
                statement.setString(3, city.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<City> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, city, idCountry FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    City city = new City(
                        resultSet.getString("id"),
                        resultSet.getString("city"),
                        resultSet.getString("idCountry")
                    );
                    return Optional.of(city);
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
            String query = "DELETE FROM city WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, city, idCountry FROM city";
            try (PreparedStatement statement = connection.prepareStatement(query); 
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        City city = new City(
                            resultSet.getString("id"),
                            resultSet.getString("city"),
                            resultSet.getString("idCountry")
                        );
                        cities.add(city);
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
