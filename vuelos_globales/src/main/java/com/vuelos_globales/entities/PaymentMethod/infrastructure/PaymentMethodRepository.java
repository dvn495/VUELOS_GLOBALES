package com.vuelos_globales.entities.PaymentMethod.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.PaymentMethod.domain.PaymentMethod;

public interface PaymentMethodRepository {
    void save(PaymentMethod paymentMethod);
    Optional<PaymentMethod> findById(int id);
    List<PaymentMethod> findAll();
}
