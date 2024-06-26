package com.vuelos_globales.entities.PaymentMethod.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.PaymentMethod.domain.PaymentMethod;
import com.vuelos_globales.entities.PaymentMethod.infrastructure.PaymentMethodRepository;

public class PayMethodMySQLRepository implements PaymentMethodRepository{
    private final String url;
    private final String user;
    private final String password;
    
    public PayMethodMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(PaymentMethod paymentMethod) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO payment_method (id, paymentMethod) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, paymentMethod.getId());
                statement.setString(2, paymentMethod.getPaymentMethod());
                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PaymentMethod> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, paymentMethod FROM payment_method WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        PaymentMethod paymentMethod = new PaymentMethod(
                            resultSet.getInt("id"),
                            resultSet.getString("paymentMethod")
                        );
                        return Optional.of(paymentMethod);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<PaymentMethod> findAll() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, paymentMethod FROM payment_method";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    PaymentMethod paymentMethod = new PaymentMethod(
                        resultSet.getInt("id"),
                        resultSet.getString("paymentMethod")
                    );
                    paymentMethods.add(paymentMethod);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentMethods;
    }

}
