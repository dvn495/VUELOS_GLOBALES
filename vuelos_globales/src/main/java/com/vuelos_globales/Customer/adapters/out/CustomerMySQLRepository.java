package com.vuelos_globales.Customer.adapters.out;

import com.vuelos_globales.Customer.infrastructure.CustomerRepository;
import com.vuelos_globales.Customer.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerMySQLRepository implements CustomerRepository {
    private final String url;
    private final String user;
    private final String password;

    public CustomerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO customer (id, name, lastName, age, idDocumentType) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getId());
                statement.setString(2, customer.getName());
                statement.setString(3, customer.getLastName());
                statement.setInt(4, customer.getIdDocumentType());
                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE customer SET name = ?, lastName = ?, age = ?, idDocumentType = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getName());
                statement.setString(2, customer.getLastName());
                statement.setInt(4, customer.getAge());
                statement.setString(5, customer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name, lastName, age, idDocumentType FROM customer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("lastName"),
                                resultSet.getInt("age"),
                                resultSet.getInt("idDocumentType")
                        );
                        return Optional.of(customer);
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
            String query = "DELETE id, name, lastName, age, idDocumentType FROM customer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name, lastName, age, idDocumentType FROM customer";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Customer customer = new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getInt("idDocumentType")
                    );
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
}
