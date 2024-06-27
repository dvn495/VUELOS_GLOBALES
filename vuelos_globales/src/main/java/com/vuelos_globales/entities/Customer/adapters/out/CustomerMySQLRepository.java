package com.vuelos_globales.entities.Customer.adapters.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.Customer.infrastructure.CustomerRepository;

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
        String query = "INSERT INTO customer (id, name, lastName, age, idDocumentType) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getLastName());
            statement.setInt(4, customer.getAge());
            statement.setInt(5, customer.getIdDocumentType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        String query = "UPDATE customer SET name = ?, lastName = ?, age = ?, idDocumentType = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getLastName());
            statement.setInt(3, customer.getAge());
            statement.setInt(4, customer.getIdDocumentType());
            statement.setString(5, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findById(String id) {
        String query = "SELECT id, name, lastName, age, idDocumentType FROM customer WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM customer WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT id, name, lastName, age, idDocumentType FROM customer";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
