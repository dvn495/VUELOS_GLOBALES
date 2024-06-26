package com.vuelos_globales.entities.Employee.adapters.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Employee.domain.Employee;
import com.vuelos_globales.entities.Employee.infrastructure.EmployeeRepository;

public class EmployeeMySQLRepository implements EmployeeRepository{
    private final String url;
    private final String user;
    private final String password;

    public EmployeeMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO employee (id, name, lastName, ingressDate, idRole, idAirline, idAirport) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getId());
                statement.setString(2, employee.getName());
                statement.setString(3, employee.getLastName());
                statement.setString(4, employee.getIngressDate());
                statement.setString(5, employee.getIdRole());
                statement.setString(6, employee.getIdAirline());
                statement.setString(7, employee.getIdAirport());
                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE employee SET name = ?, lastName = ?, ingressDate = ?, idRole = ?, idAirline = ?, idAirport = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getId());
                statement.setString(2, employee.getName());
                statement.setString(3, employee.getLastName());
                statement.setString(4, employee.getIngressDate());
                statement.setString(5, employee.getIdRole());
                statement.setString(6, employee.getIdAirline());
                statement.setString(7, employee.getIdAirport());
                statement.executeUpdate();
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name, lastName, idDocumentType, ingressDate, idRole, idAirline, idAirport FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Employee employee = new Employee(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("lastName"),
                                resultSet.getString("ingressDate"),
                                resultSet.getString("idRole"),
                                resultSet.getString("idAirline"),
                                resultSet.getString("idAirport")
                        );
                        return Optional.of(employee);
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
            String query = "DELETE id, name, lastName, idDocumentType, ingressDate, idRole, idAirline, idAirport FROM employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name, lastName, age, idDocumentType FROM employee";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Employee employee = new Employee(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getString("ingressDate"),
                        resultSet.getString("idRole"),
                        resultSet.getString("idAirline"),
                        resultSet.getString("idAirport")
                    );
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
