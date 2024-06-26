package com.vuelos_globales.entities.PaymentMethod.application;

import com.vuelos_globales.entities.PaymentMethod.domain.PaymentMethod;
import com.vuelos_globales.entities.PaymentMethod.infrastructure.PaymentMethodRepository;

import java.util.Optional;
import java.util.List;

public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void createPayMethod(PaymentMethod paymentMethod) {
        paymentMethodRepository.save(paymentMethod);
    }

    public Optional<PaymentMethod> getPayMethodById(int id) {
        return paymentMethodRepository.findById(id);
    }

    public List<PaymentMethod> getAllPayMethods() {
        return paymentMethodRepository.findAll();
    }
}
