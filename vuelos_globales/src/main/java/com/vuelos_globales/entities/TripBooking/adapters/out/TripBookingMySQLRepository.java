package com.vuelos_globales.entities.TripBooking.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;
import com.vuelos_globales.entities.FlightFares.domain.FlightFares;

public class TripBookingMySQLRepository implements TripBookingRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripBookingMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripBooking tripBooking) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(tripBooking.getBookingDate());
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip_booking (id, bookingDate, idTrip, idBookingStatus, idCustomer) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripBooking.getId());
                statement.setDate(2, sqlDate);
                statement.setString(3, tripBooking.getIdTrip());
                statement.setInt(4, tripBooking.getIdBookingStatus());
                statement.setString(5, tripBooking.getIdCustomer());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TripBooking tripBooking) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(tripBooking.getBookingDate());
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip_booking SET bookingDate = ?, idTrip = ?, idBookingStatus = ?, idCustomer = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, sqlDate);
                statement.setString(2, tripBooking.getIdTrip());
                statement.setInt(3, tripBooking.getIdBookingStatus());
                statement.setString(4, tripBooking.getIdCustomer());
                statement.setString(5, tripBooking.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<TripBooking> findById(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, bookingDate, idTrip, idBookingStatus, idCustomer FROM trip_booking WHERE id = ?";
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
                            resultSet.getInt("idBookingStatus"),
                            resultSet.getString("idCustomer")
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

    @Override
    public void delete(String id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM trip_booking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TripBooking> findAll() {
        List<TripBooking> tripBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, bookingDate, idTrip, idBookingStatus, idCustomer FROM trip_booking";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    java.sql.Date sqlDate = resultSet.getDate("bookingDate");
                    LocalDate bookingDate = sqlDate.toLocalDate();
                    TripBooking tripBooking = new TripBooking(
                        resultSet.getString("id"),
                        bookingDate,
                        resultSet.getString("idTrip"),
                        resultSet.getInt("idBookingStatus"),
                        resultSet.getString("idCustomer")
                    );
                    tripBookings.add(tripBooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripBookings;
    }

    
    @Override
    public Optional<FlightFares> findFlightFareByTripBId(String id) {
        Optional<FlightFares> flightFare = Optional.empty();
        String query = "SELECT ff.id, ff.description, ff.details, ff.value " +
                       "FROM trip_booking tb " +
                       "JOIN trip_booking_details tbd ON tb.id = tbd.idTripBooking " +
                       "JOIN flight_fares ff ON tbd.idFlightFares = ff.id " +
                       "WHERE tb.id = ?";
        
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String flightFaresId = resultSet.getString("id");
                    String description = resultSet.getString("description");
                    String details = resultSet.getString("details");
                    double value = resultSet.getDouble("value");
                    
                    flightFare = Optional.of(new FlightFares(flightFaresId, description, details, value));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return flightFare;
    }
}

