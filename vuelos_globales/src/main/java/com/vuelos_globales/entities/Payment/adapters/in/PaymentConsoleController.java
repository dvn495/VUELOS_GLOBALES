package com.vuelos_globales.entities.Payment.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.List;

import com.vuelos_globales.entities.Payment.domain.Payment;
import com.vuelos_globales.entities.Payment.application.PaymentService;
import com.vuelos_globales.entities.PaymentMethod.domain.PaymentMethod;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.modules.ConsoleUtils;

public class PaymentConsoleController {
    Scanner sc = new Scanner(System.in);

    private final PaymentService paymentService;

    public PaymentConsoleController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // METODO DE PAGO

    public void createPaymentMethod() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR METODO PAGO ***************");
            
            System.out.println("[*] INGRESE EL ID DEL METODO PAGO A CREAR: [NUMERO ENTERO]");
            int pMtID = Integer.parseInt(sc.nextLine());

            Optional<PaymentMethod> paymentMethod = paymentService.getPaymentMethodById(pMtID);
            paymentMethod.ifPresentOrElse(
                pm -> {
                    System.out.println(MessageFormat.format("[!] EL ID {0} YA ESTA OCUPADO.", pm.getId()));
                    ConsoleUtils.esperarEntrada();
                },
                () -> {
                    System.out.println("[*] INGRESE LA DESCRIPCION DEL METODO PAGO A CREAR:");
                    String desc = sc.nextLine();
        
                    PaymentMethod newPaymentMethod = new PaymentMethod(pMtID, desc);
                    paymentService.createPaymentMethod(newPaymentMethod);
                });


            System.out.println();
        }
    }

    public void createTripBooking() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR RESERVA DE VIAJE ***************");
            System.out.println("[*] INGRESE EL ID DE LA RESERVA DE VIAJE A CREAR: ");
            String id = sc.nextLine();
            Optional<TripBooking> tripBooking = paymentService.getTripBookingById(id);
            tripBooking.ifPresentOrElse(
                t -> {
                    System.out.println(MessageFormat.format("[!] LA RESERVA CON ID (0) YA ESTA OCUPADA.", t.getId()));
                },
                () -> {
                    ConsoleUtils.esperarEntrada();
                    System.out.println("*************** REGISTRAR RESERVA DE VIAJE ***************");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaReserva = null;
                    boolean isActiveDate = false;
                    String newDate = "";

                    while (!isActiveDate) {
                        System.out.println("\n[*] INGRESE LA FECHA DE RESERVA DE VIAJE (dd-MM-yyyy)");
                        System.out.print("INGRESE LA FECHA DE LA RESERVA DE VIAJE: ");
                        newDate = sc.nextLine();

                        try {
                            fechaReserva = LocalDate.parse(newDate, formatter);
                            isActiveDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                        }
                    }

                    System.out.println("\n[*]  INGRESE EL ID DEL VIAJE: ");
                    String newIdTrip = sc.nextLine();

                    System.out.println("\n[*]  INGRESE EL ID DEL ESTADO DE RESERVA: ");
                    int newIdStatus = sc.nextInt();

                    System.out.println("\n[*]  INGRESE EL ID DEL CLIENTE: ");
                    String newIdCustomer = sc.nextLine();

                    TripBooking newTripBooking = new TripBooking(id, fechaReserva, newIdTrip, newIdStatus, newIdCustomer);
                    paymentService.createTripBooking(newTripBooking);
                });
            System.out.println("[?] DESEA AÑADIR OTRA RESERVA DE VIAJE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void createPayment() {
        String rta = "S";

        while (rta.equalsIgnoreCase(rta)) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR PAGO ***************");
            System.out.println("[*] INGRESE EL ID DEL PAGO A CREAR: ");
            String id = sc.nextLine();
            Optional<Payment> payment = paymentService.getPaymentById(id);
            payment.ifPresentOrElse(
                p -> {
                    System.out.println(MessageFormat.format("[!] EL ID {0} YA ESTA OCUPADO.", p.getId()));
                    ConsoleUtils.esperarEntrada();
                },
                () -> {
                    System.out.println("[*] INGRESE EL MONTO TOTAL DEL PAGO: ");
                    double newAmount = sc.nextDouble();

                    System.out.println("[*] INGRESE EL METODO DE PAGO: ");
                    List<PaymentMethod> paymentMethods = paymentService.getAllPaymentMethods();
                    if (paymentMethods.isEmpty()) {
                        createPaymentMethod();
                    } else {
                        paymentMethods.forEach(pm -> {
                            System.out.println(MessageFormat.format("[{0}] - {1}", pm.getId(), pm.getPaymentMethod()));
                        });
                    }
                    int idPayMethod = Integer.parseInt(sc.nextLine());

                    String newAccount;
                    boolean isActiveRegCreditNum = true;
                    while (isActiveRegCreditNum) {
                        System.out.println("[*] INGRESE EL NUMERO DE CUENTA: ");
                        newAccount = sc.nextLine();

                        if (newAccount.length() > 16) {
                            System.out.println("[!] INGRESA UN NUMERO VALIDO DE CUENTA.");
                            ConsoleUtils.esperarEntrada();
                            newAccount = "";
                        } else {
                            isActiveRegCreditNum = false;
                        }
                    }

                    ConsoleUtils.limpiarConsola();
                    List<TripBooking>  tripBookings = paymentService.getAllTripBookings();
                    if (tripBookings.isEmpty()) {
                        createTripBooking();
                    }
                    tripBookings.forEach(tb -> {
                        System.out.println(MessageFormat.format("[{0}] - VIAJE : {1}", tb.getId(), tb.getIdTrip()));
                    });
                    
                    System.out.println("[*] INGRESE EL ID DE LA RESERVA DE VIAJE: ");
                    String idTripBooking = sc.nextLine();

                    Payment newPayment = new Payment(id, newAmount, idPayMethod, rta, idTripBooking);
                    paymentService.createPayment(newPayment);
                });
        }
    }

}
