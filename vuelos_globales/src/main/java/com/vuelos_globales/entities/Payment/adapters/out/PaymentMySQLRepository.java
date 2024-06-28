package com.vuelos_globales.entities.Payment.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Payment.infrastructure.PaymentRepository;
import com.vuelos_globales.entities.Payment.domain.Payment;
import com.vuelos_globales.entities.Employee.domain.Employee;
import com.vuelos_globales.entities.Payment.domain.Payment;

public class PaymentMySQLRepository implements PaymentRepository {
    private final String url;
    private final String user;
    private final String password;

    public PaymentMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Payment payment) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO payment (id, amount, paymentMethod, creditCardNumber, idTripBookingDetails) VALUES (?, ?, ?, ? , ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, payment.getId());
                preparedStatement.setDouble(2, payment.getAmount());
                preparedStatement.setInt(3, payment.getIdPaymentMethod());
                preparedStatement.setString(4, payment.getCreditCardNumber());
                preparedStatement.setString(5, payment.getIdTripBookingDetails());
                preparedStatement.executeUpdate();
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payment payment) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE payment SET amount = ?, paymentMethod = ?, creditCardNumber = ?, idTripBookingDetails = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, payment.getAmount());
                statement.setInt(2, payment.getIdPaymentMethod());
                statement.setString(3, payment.getCreditCardNumber());
                statement.setString(4, payment.getIdTripBookingDetails());
                statement.setString(5, payment.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Payment> findById(String id) {
        String query = "SELECT id, amount, paymentMethod, creditCardNumber, idTripBookingDetails FROM payment WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Payment payment = new Payment(
                            resultSet.getString("id"),
                            resultSet.getDouble("amount"),
                            resultSet.getInt("paymentMethod"),
                            resultSet.getString("creditCardNumber"),
                            resultSet.getString("idTripBookingDetails")
                    );
                    return Optional.of(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT id, amount, paymentMethod, creditCardNumber, idTripBookingDetails FROM payment";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Payment payment = new Payment(
                    resultSet.getString("id"),
                    resultSet.getDouble("amount"),
                    resultSet.getInt("paymentMethod"),
                    resultSet.getString("creditCardNumber"),
                    resultSet.getString("idTripBookingDetails")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
    
    @Override
    public void delete(String id) {
        String query = "DELETE FROM payment WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
