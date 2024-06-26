package com.vuelos_globales.entities.Payment.application;

import com.vuelos_globales.entities.Payment.domain.Payment;
import com.vuelos_globales.entities.Payment.infrastructure.PaymentRepository;

import java.util.Optional;
import java.util.List;


public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void createPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void updatePayment(Payment payment) {
        paymentRepository.update(payment);
    }

    public Optional<Payment> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }

    public void deletePayment(String id) {
        paymentRepository.delete(id);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
