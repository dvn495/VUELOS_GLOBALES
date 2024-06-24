package com.vuelos_globales.Country.adapters.out;


import com.vuelos_globales.Country.infrastructure.CountryRepository;
import com.vuelos_globales.Country.domain.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryMySQLRepository implements CountryRepository {
    private final String url;
    private final String user;
    private final String password;
    
    public CountryMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Country country) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO country (id, country) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getId());
                statement.setString(2, country.getCountryName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Country country) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE country SET country = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, country.getCountryName());
                statement.setString(2, country.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, country FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    Country country = new Country(
                        resultSet.getString("id"),
                        resultSet.getString("country")
                    );
                    return Optional.of(country);
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
            String query = "DELETE FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, country FROM country";
            try (PreparedStatement statement = connection.prepareStatement(query); 
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Country Country = new Country(
                            resultSet.getString("id"),
                            resultSet.getString("country")
                        );
                        countries.add(Country);
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

}
