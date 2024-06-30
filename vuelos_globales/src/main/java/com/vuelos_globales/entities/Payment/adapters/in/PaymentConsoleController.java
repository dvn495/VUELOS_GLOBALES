package com.vuelos_globales.entities.TripBooking.adapters.in;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.TripBooking.application.TripBookingService;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.modules.ConsoleUtils;

import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.Planes.domain.Planes;

// INYECCIONES

import com.vuelos_globales.entities.Customer.adapters.in.CustomerConsoleAdapter;
import com.vuelos_globales.entities.Customer.application.CustomerService;

public class TripBookingConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final TripBookingService tripBookingService;

    private final CustomerService customerService;


    public TripBookingConsoleAdapter(TripBookingService tripBookingService, CustomerService customerService) {
        this.tripBookingService = tripBookingService;
        this.customerService = customerService;
    }

    public void createTripBooking() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR RESERVA DE VIAJE ***************");
            System.out.println("[*] INGRESE EL ID DE LA RESERVA DE VIAJE A CREAR: ");
            String id = sc.nextLine();
            Optional<TripBooking> tripBooking = tripBookingService.getTripBookingById(id);
            tripBooking.ifPresentOrElse(
                    t -> {
                        System.out
                                .println(MessageFormat.format("[!] LA RESERVA CON ID (0) YA ESTA OCUPADA.", t.getId()));
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
                            newDate = sc.nextLine();

                            try {
                                fechaReserva = LocalDate.parse(newDate, formatter);
                                isActiveDate = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                                sc.nextLine();
                                isActiveDate = false;
                            }
                        }

                        System.out.println("\n[*]  INGRESE EL ID DEL VIAJE: ");
                        String newIdTrip = sc.nextLine();

                        System.out.println("\n[*]  INGRESE EL ID DEL ESTADO DE RESERVA: ");
                        int newIdStatus = Integer.parseInt(sc.nextLine());

                        System.out.println("\n[*]  INGRESE EL ID DEL CLIENTE: ");
                        String newIdCustomer = sc.nextLine();

                        TripBooking newTripBooking = new TripBooking(id, fechaReserva, newIdTrip, newIdStatus,
                                newIdCustomer);
                        tripBookingService.createTripBooking(newTripBooking);
                    });
            System.out.println("[?] DESEA AÑADIR OTRA RESERVA DE VIAJE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchTripBooking() {
        List<TripBooking> tripBookings = tripBookingService.getAllTripBookings();

        if (tripBookings.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUNA RESERVA DE VIAJE REGISTRADA");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR RESERVA DE VIAJE ***************");
            System.out.println("[?] INGRESE EL ID DE LA RESERVA DE VIAJE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<TripBooking> tripBooking = tripBookingService.getTripBookingById(findId);
            tripBooking.ifPresentOrElse(
                    t -> {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("*************** RESERVA DE VIAJE ***************");
                        System.out.println(MessageFormat.format(
                                "[*] ID : {0}\n[*] FECHA DE RESERVA : {1}\n[*] ID DE VIAJE: {2}\n[*] ID DE ESTADO DE RESERVA: {3}\n[*] ID DEL CLIENTE: {4}",
                                t.getId(), t.getBookingDate() + t.getIdTrip(), t.getIdBookingStatus(),
                                t.getIdCustomer()));
                        sc.nextLine();
                    },
                    () -> {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("[!]  RESERVA DE VIAJE NO ENCONTRADA");
                        sc.nextLine();
                    });
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void updateTripBooking() {
        ConsoleUtils.limpiarConsola();
        List<TripBooking> tripBookings = tripBookingService.getAllTripBookings();

        if (tripBookings.isEmpty()) {

            System.out.println("[!]  NO HAY RESERVA DE VIAJE REGISTRADAS");

        } else {

            System.out.println("[*]  INGRESE EL ID DEL RESERVA DE VIAJE A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<TripBooking> tripBooking = tripBookingService.getTripBookingById(findId);
            tripBooking.ifPresentOrElse(
                    t -> {
                        System.out.println(MessageFormat.format(
                                "[*] ID : {0}\n[*] FECHA DE RESERVA : {1}\n[*] ID DE VIAJE: {2}\n[*] ID DE ESTADO DE RESERVA: {3}\n[*] ID DEL CLIENTE: {4}",
                                t.getId(), t.getBookingDate() + t.getIdTrip(), t.getIdBookingStatus(),
                                t.getIdCustomer()));

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate fechaReserva = null;
                        boolean isActiveDate = false;
                        String newDate = "";

                        while (!isActiveDate) {
                            System.out.println("\n[*] INGRESE LA FECHA DE RESERVA DE VIAJE (dd-MM-yyyy)");
                            System.out.print("INGRESE LA FECHA DE RESERVA DE VIAJE: ");
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
                        int newIdStatus = Integer.parseInt(sc.nextLine());

                        System.out.println("\n[*]  INGRESE EL ID DEL CLIENTE: ");
                        String newIdCustomer = sc.nextLine();

                        TripBooking newTripBooking = new TripBooking(findId, fechaReserva, newIdTrip, newIdStatus,
                                newIdCustomer);
                        tripBookingService.updateTripBooking(newTripBooking);
                    },
                    () -> System.out.println("[!]  RESERVA DE VIAJE NO ENCONTRADA"));
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void deleteTripBooking() {
        ConsoleUtils.limpiarConsola();
        List<TripBooking> tripBookings = tripBookingService.getAllTripBookings();
        if (tripBookings.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUNA RESERVA DE VIAJE REGISTRADA");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DE LA RESERVA DE VIAJE A ELIMINAR\n\n");
            String findId = sc.nextLine();

            Optional<TripBooking> tripBooking = tripBookingService.getTripBookingById(findId);
            tripBooking.ifPresentOrElse(
                    c -> {
                        tripBookingService.deleteTripBooking(findId);
                        System.out.println("[!] RESERVA DE VIAJE ELIMINADA CORRECTAMENTE.");
                        sc.nextLine();
                    },
                    () -> {
                        System.out.println("[!]  RESERVA DE VIAJE NO ENCONTRADO");

                    });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllTripBookings() {
        List<TripBooking> TripBookings = tripBookingService.getAllTripBookings();

        if (TripBookings.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUNA RESERVA DE VIAJE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            tripBookingService.getAllTripBookings().forEach(t -> {
                System.out.println(MessageFormat.format(
                        "[*] ID : {0}\n[*] FECHA DE RESERVA : {1}\n[*] ID DE VIAJE: {2}\n[*] ID DE ESTADO DE RESERVA: {3}\n[*] ID DEL CLIENTE: {4}",
                        t.getId(), t.getBookingDate() + t.getIdTrip(), t.getIdBookingStatus(), t.getIdCustomer()));
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllCustomers() {
        List<Customer> customers = tripBookingService.getAllCustomers();
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[*] VUELOS DISPONIBLES");
            customers.forEach(c -> {
                System.out.println(
                        MessageFormat.format("[*] ID : {0} - : {1}", c.getId(), c.getName() + c.getLastName()));
            });
        }
    }

    static boolean isActiveSel = true;
    static int seatNumber = 0;
    static LocalDate fechaReserva = null;
    public void selectTripBooking() {
        isActiveSel = true;
        while (isActiveSel) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[*] INGRESE UN ID PARA LA RESERVA DE VUELO");
            String id = sc.nextLine();
            tripBookingService.getTripBookingById(id).ifPresentOrElse(
                    tb -> {
                        System.out.println(MessageFormat.format("[!] EL ID {0} YA ESTA OCUPADO.", tb.getId()));
                    },
                    () -> {
                        // TRIP DATE
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        boolean isActiveDate = false;
                        String newDate = "";

                        while (!isActiveDate) {
                            System.out.println("\n[*] INGRESE LA FECHA DE RESERVA DE VIAJE (dd-MM-yyyy)");
                            System.out.print("INGRESE LA FECHA DE RESERVA DE VIAJE: ");
                            newDate = sc.nextLine();

                            try {
                                fechaReserva = LocalDate.parse(newDate, formatter);
                                isActiveDate = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                            }
                        }

                        ConsoleUtils.limpiarConsola();
                        System.out.println("[*] SELECCIONE UN VUELO: ");
                        tripBookingService.getAllTrips().forEach(v -> {
                            System.out.println(MessageFormat.format("[{0}] - {1}", v.getId(), v.getTripDate()));
                        });
                        System.out.println("[?] INGRESE EL ID DEL VUELO: ");
                        String idTrip = sc.nextLine();

                        ConsoleUtils.limpiarConsola();
                        System.out.println("[*] CLIENTES: ");
                        if (tripBookingService.getAllCustomers().isEmpty()) {
                            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
                            System.out.println("[?] DESEA REGISTRAR UN CLIENTE?  [S/N]");
                            String answer = sc.nextLine();
                            if (answer.equalsIgnoreCase("S")) {
                                CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(
                                        customerService);
                                customerConsoleAdapter.createCustomer();
                                isActiveSel = false;
                            }

                        } else {
                            tripBookingService.getAllCustomers().forEach(c -> {
                                System.out.println(
                                        MessageFormat.format("[{0}] - {1}", c.getId(), c.getName() + c.getLastName()));
                            });

                        }
                        System.out.println("[?] INGRESE EL ID DEL PASAJERO: ");
                        String idCustomer = sc.nextLine();

                        ConsoleUtils.limpiarConsola();

                        System.out.println("[*] AVIONES DISPONIBLES");
                        tripBookingService.getAllAirplanes().forEach(
                                p -> {
                                    System.out.println(MessageFormat.format("[{0}] - {1}", p.getId(), p.getPlates()));
                                });

                        System.out.println("[*] INGRESE EL ID DEL AVION");
                        String planeId = sc.nextLine();
                        tripBookingService.getPlaneById(planeId).ifPresentOrElse(
                                a -> {

                                    Optional<Planes> avion = tripBookingService.getPlaneById(planeId);
                                    avion.ifPresentOrElse(
                                            p -> {
                                                ConsoleUtils.limpiarConsola();
                                                System.out.println("[*] MAPA DE ASIENTOS DISPONIBLES DEL AVION");

                                                int numAsientos = tripBookingService.getCapacity(planeId);
                                                ArrayList<Object> mapa = new ArrayList<>();

                                                for (int i = 1; i < numAsientos; i++) {
                                                    mapa.add(i);
                                                }

                                                for (int i = 0; i < mapa.size(); i++) {
                                                    System.out.print(mapa.get(i) + "\t");
                                                    if ((i + 1) % 8 == 0) {
                                                        System.out.println();
                                                    }
                                                }
                                                seatNumber = Integer.parseInt(sc.nextLine());
                                                mapa.remove(seatNumber);

                                                // BOOKING STATUS
                                                ConsoleUtils.limpiarConsola();
                                                System.out.println("[*] SELECCIONE EL ESTADO DE LA RESERVA");
                                                int j = 1;
                                                for (String bs : tripBookingService.getAllBookingStatuses()) {
                                                    System.out.println(MessageFormat.format("{0} , {1}", j++, bs));
                                                }
                                                int bookingStatus = Integer.parseInt(sc.nextLine());

                                                TripBooking newTripBooking = new TripBooking(id, fechaReserva, idTrip, bookingStatus, idCustomer);
                                                tripBookingService.createTripBooking(newTripBooking);
                                            }, () -> {
                                                ConsoleUtils.limpiarConsola();
                                                System.out.println("[!] NO SE ENCONTRO EL AVION");
                                                sc.nextLine();
                                                isActiveSel = false;
                                            });
                                },
                                () -> {
                                    System.out.println(
                                            "[!] NO SE HA ENCONTRADO EL CLIENTE. DESEAS REGISTRAR UNO NUEVO?  [S/N]");
                                    String respuesta = sc.nextLine();
                                    if (respuesta.equalsIgnoreCase("S")) {
                                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(
                                                customerService);

                                        customerConsoleAdapter.createCustomer();
                                    } else {
                                        isActiveSel = false;
                                    }
                                });
                    });
        }
    }
}