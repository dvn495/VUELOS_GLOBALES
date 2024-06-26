package com.vuelos_globales.entities.RevEmployee.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.RevEmployee.infrastructure.RevEmployeesRepository;
import com.vuelos_globales.entities.RevEmployee.domain.RevEmployees;

public class RevEmployeesMySQLRepository implements RevEmployeesRepository{
    private final String url;
    private final String user;
    private final String password;

    public RevEmployeesMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(RevEmployees RevEmployees){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO revision_employee (id, idEmployee, idRevision) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, RevEmployees.getId());
                statement.setString(2, RevEmployees.getIdEmployee());
                statement.setString(3, RevEmployees.getIdRevision());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(RevEmployees RevEmployees){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE revision_employee SET idEmployee = ?, idRevision = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, RevEmployees.getIdEmployee());
                statement.setString(2, RevEmployees.getIdRevision());
                statement.setString(3, RevEmployees.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<RevEmployees> findById(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM revision_employee WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        RevEmployees RevEmployees = new RevEmployees(
                            resultSet.getString("id"),
                            resultSet.getString("idEmployee"),
                            resultSet.getString("idRevision")
                        );
                        return Optional.of(RevEmployees);
                    }

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override 
    public void delete(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM revision_employee WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<RevEmployees> findAll(){
        List<RevEmployees> RevEmployees = new ArrayList<>();
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM revision_employee";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()){
                        RevEmployees revEmployee = new RevEmployees(
                            resultSet.getString("id"),
                            resultSet.getString("idEmployee"),
                            resultSet.getString("idRevision")
                        );
                        RevEmployees.add(revEmployee);
                    }
                }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return RevEmployees;
    }
}
