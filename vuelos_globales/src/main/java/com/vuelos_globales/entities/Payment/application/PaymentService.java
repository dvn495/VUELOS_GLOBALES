package com.vuelos_globales.entities.Payment.application;

import com.vuelos_globales.entities.Payment.domain.Payment;
import com.vuelos_globales.entities.Payment.infrastructure.PaymentRepository;

import java.util.Optional;
import java.util.List;

import com.vuelos_globales.entities.PaymentMethod.domain.PaymentMethod;
import com.vuelos_globales.entities.PaymentMethod.infrastructure.PaymentMethodRepository;

import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.infrastructure.TripBookingRepository;

public class PaymentService {
    private final PaymentRepository paymentRepository;

    private final PaymentMethodRepository paymentMethodRepository;

    private final TripBookingRepository tripBookingRepository;

    public PaymentService(PaymentRepository paymentRepository, PaymentMethodRepository paymentMethodRepository, TripBookingRepository tripBookingRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.tripBookingRepository = tripBookingRepository;
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

    // METODO DE PAGO

    public void createPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethodRepository.save(paymentMethod);
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public Optional<PaymentMethod> getPaymentMethodById(int id) {
        return paymentMethodRepository.findById(id);
    }

    // RESERVA DE VIAJE

    public void createTripBooking(TripBooking tripBooking) {
        tripBookingRepository.save(tripBooking);
    }

    public List<TripBooking> getAllTripBookings() {
        return tripBookingRepository.findAll();
    }

    public Optional<TripBooking> getTripBookingById(String id) {
        return tripBookingRepository.findById(id);
    }

}
