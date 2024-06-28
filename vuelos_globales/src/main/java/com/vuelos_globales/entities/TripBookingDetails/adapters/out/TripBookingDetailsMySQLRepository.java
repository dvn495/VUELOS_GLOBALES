package com.vuelos_globales.entities.TripBookingDetails.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripBookingDetails.domain.TripBookingDetails;
import com.vuelos_globales.entities.TripBookingDetails.infrastructure.TripBookingDetailsRepository;


public class TripBookingDetailsMySQLRepository implements TripBookingDetailsRepository {
    private final String url;
    private final String user;
    private final String password;

    
    public TripBookingDetailsMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
        public void save(TripBookingDetails tripBookingDetails){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO trip_booking_details (id, seatNumber, idTripBooking , idFlightFares) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, tripBookingDetails.getId());
                statement.setInt(2, tripBookingDetails.getSeatNumber());
                statement.setString(3, tripBookingDetails.getIdTripBooking());
                statement.setString(4, tripBookingDetails.getIdFlightFares());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void update(TripBookingDetails tripBookingDetails) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip_booking_details SET idTripBooking = ?, idFlightFares = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBookingDetails.getIdTripBooking());
                statement.setString(3, tripBookingDetails.getIdFlightFares());
                statement.setString(4, tripBookingDetails.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public Optional<TripBookingDetails> findById(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM trip_booking_details WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        TripBookingDetails tripBookingDetails = new TripBookingDetails(
                            resultSet.getString("id"),
                            resultSet.getInt("seatNumber"),
                            resultSet.getString("idTripBooking"),
                            resultSet.getString("idFlightFares")
                        );
                        return Optional.of(tripBookingDetails);
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
            String query = "DELETE FROM trip_booking_details WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<TripBookingDetails> findAll(){
        List<TripBookingDetails> tripBookingDetails = new ArrayList<>();
        try (Connection connection =  DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, idTripBooking, , idFlightFares FROM trip_booking_details";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()){
                        TripBookingDetails tripBookingDetail = new TripBookingDetails(
                            resultSet.getString("id"),
                            resultSet.getInt("seatNumber"),
                            resultSet.getString("idTripBooking"),
                            resultSet.getString("idFlightFares")
                        );
                        tripBookingDetails.add(tripBookingDetail);
                    }
                }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tripBookingDetails;
    }

    
}
