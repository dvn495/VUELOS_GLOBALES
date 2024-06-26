package com.vuelos_globales.entities.TripBooking.adapters.out;

import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripBookingMySQLRepository implements TripBookingRepository{
    private final String url;
    private final String user;
    private final String password;

    public TripBookingMySQLRepository(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripBooking tripBooking){
        java.sql.Date sqlDate = java.sql.Date.valueOf(tripBooking.getBookingDate());
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO trip_booking (id, bookingDate, idTrip, idBookingStatus) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, tripBooking.getId());
                statement.setDate(2, sqlDate);
                statement.setString(3, tripBooking.getIdTrip());
                statement.setString(4, tripBooking.getIdBookingStatus());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(TripBooking tripBooking){
        java.sql.Date sqlDate = java.sql.Date.valueOf(tripBooking.getBookingDate());
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE trip_booking SET bookingDate = ?, idTrip = ?, idBookingStatus = ? WHERE id = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, tripBooking.getId());
                statement.setDate(2, sqlDate);
                statement.setString(3, tripBooking.getIdTrip());
                statement.setString(4, tripBooking.getIdBookingStatus());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripBooking> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, bookingDate, idTrip, idBookingStatus FROM trip_booking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        java.sql.Date sqlDate = resultSet.getDate("bookingDate");
                        LocalDate bookingDate = sqlDate.toLocalDate();
                        TripBooking tripBooking = new TripBooking(
                            resultSet.getString("id"),
                            bookingDate,
                            resultSet.getString("idTrip"),
                            resultSet.getString("idBookingStatus")
                        );
                        return Optional.of(tripBooking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override public void delete(String id){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM trip_booking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<TripBooking> findAll(){
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, bookingDate, idTrip, idBookingStatus FROM trip_booking";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    java.sql.Date sqlDate = resultSet.getDate("bookinDate");
                    LocalDate bookinDate = sqlDate.toLocalDate();
                    TripBooking tripBooking = new TripBooking(
                        resultSet.getString("id"),
                        bookinDate,
                        resultSet.getString("idTrip"),
                        resultSet.getString("idBookingStatus")
                    );
                    tripBookings.add(tripBooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }
}

