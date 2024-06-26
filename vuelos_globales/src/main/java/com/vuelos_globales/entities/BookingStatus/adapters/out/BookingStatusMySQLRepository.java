package com.vuelos_globales.entities.BookingStatus.adapters.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.BookingStatus.infrastructure.BookingStatusRepository;

public class BookingStatusMySQLRepository implements BookingStatusRepository {
    private final String url;
    private final String user;
    private final String password;
    
    public BookingStatusMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

        @Override
    public void save(BookingStatus bookingStatus) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO booking_status (id, bookingStatus) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bookingStatus.getId());
                statement.setString(2, bookingStatus.getBookingStatus());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<BookingStatus> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, bookingStatus FROM booking_status WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        BookingStatus bookingStatus = new BookingStatus(
                            resultSet.getInt("id"),
                            resultSet.getString("bookingStatus")
                        );
                        return Optional.of(bookingStatus);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    }
    
    @Override
    public List<BookingStatus> findAll() {
        List<BookingStatus> bookingStatuses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, bookingStatus FROM booking_status";
            try (PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    BookingStatus bookingStatus = new BookingStatus(
                        resultSet.getInt("id"),
                        resultSet.getString("bookingStatus")
                    );
                    bookingStatuses.add(bookingStatus);
                }
            } 
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return bookingStatuses;
    }
}
