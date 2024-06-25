package com.vuelos_globales.Manufactures.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Manufactures.domain.Manufactures;
import com.vuelos_globales.Manufactures.infrastructure.ManufacturesRepository;

public class ManufacturesMySQLRepository implements ManufacturesRepository {
    private final String url;
    private final String user;
    private final String password;

    public ManufacturesMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Manufactures manufactures){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO manufacturer (id, manufacturer) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, manufactures.getId());
                statement.setString(2, manufactures.getManufacturer());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Manufactures manufactures){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE manufacturer SET manufacturer = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, manufactures.getManufacturer());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Manufactures> findById(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM manufacturer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        Manufactures manufactures = new Manufactures(
                            resultSet.getString("id"),
                            resultSet.getString("manufacturer")
                        );
                        return Optional.of(manufactures);
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
            String query = "DELETE FROM manufacturer WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Manufactures> findAll(){
        List<Manufactures> manufactures = new ArrayList<>();
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM manufacturer";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()){
                        Manufactures manufacture = new Manufactures(
                            resultSet.getString("id"),
                            resultSet.getString("manufacturer")
                        );
                        manufactures.add(manufacture);
                    }
                }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return manufactures;
    }
}
