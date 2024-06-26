package com.vuelos_globales.entities.Gates.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Gates.domain.Gates;
import com.vuelos_globales.entities.Gates.infrastructure.GatesRepository;

public class GatesMySQLRepository implements GatesRepository {
    private final String url;
    private final String user;
    private final String password;

    public GatesMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Gates gates){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO gate (id, gate, idAirport) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, gates.getId());
                statement.setString(2, gates.getGate());
                statement.setString(3, gates.getIdAirport());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Gates gates){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE gate SET gate = ?, idAirport = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, gates.getGate());
                statement.setString(2, gates.getIdAirport());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Gates> findById(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM gate WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        Gates gates = new Gates(
                            resultSet.getString("id"),
                            resultSet.getString("gate"),
                            resultSet.getString("idAirport")
                        );
                        return Optional.of(gates);
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
            String query = "DELETE FROM gate WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Gates> findAll(){
        List<Gates> gates = new ArrayList<>();
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM gate";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()){
                        Gates gate = new Gates(
                            resultSet.getString("id"),
                            resultSet.getString("gate"),
                            resultSet.getString("idAirport")
                        );
                        gates.add(gate);
                    }
                }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return gates;
    }

}
