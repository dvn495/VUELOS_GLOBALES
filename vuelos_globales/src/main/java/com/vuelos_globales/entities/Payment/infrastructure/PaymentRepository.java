package com.vuelos_globales.entities.Payment.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Payment.domain.Payment;

public interface PaymentRepository {
    void save(Payment payment);
    void update(Payment payment);
    Optional<Payment> findById(String id);
    void delete(String id);
    List<Payment> findAll();
}
