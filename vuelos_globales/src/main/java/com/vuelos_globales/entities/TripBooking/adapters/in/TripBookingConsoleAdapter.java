package com.vuelos_globales.entities.TripBooking.adapters.in;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import com.vuelos_globales.modules.ConsoleUtils;
import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBooking.application.TripBookingService;

import java.util.List;
import java.util.Optional;

public class TripBookingConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final TripBookingService tripBookingService;

    public TripBookingConsoleAdapter(TripBookingService tripBookingService) {
        this.tripBookingService = tripBookingService;
    }

    public void createTripBooking(){
        String rta = "S";

        while (rta.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR RESERVA DE VIAJE ***************");
            System.out.println("[*] INGRESE EL ID DE LA RESERVA DE VIAJE A CREAR: ");
            String id = sc.nextLine();
            Optional<TripBooking> tripBooking = tripBookingService.getTripBookingById(id);
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
                    String newIdStatus = sc.nextLine();

                    TripBooking newTripBooking = new TripBooking(id, fechaReserva, newIdTrip, newIdStatus);
                    tripBookingService.updateTripBooking(newTripBooking);
                });
            System.out.println("[?] DESEA AÑADIR OTRA RESERVA DE VIAJE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }


    public void searchTripBooking(){
        List<TripBooking> tripBookings = tripBookingService.getAllTripBookings();

        if(tripBookings.isEmpty()){
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
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] FECHA DE RESERVA : {1}\n[*] ID DE VIAJE: {2}\n[*] ID DE ESTADO DE RESERVA: {3}", t.getId(), t.getBookingDate() + t.getIdTrip(), t.getIdBookingStatus()));
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


    public void updateTripBooking(){
        ConsoleUtils.limpiarConsola();
        List<TripBooking> tripBookings = tripBookingService.getAllTripBookings();

        if(tripBookings.isEmpty()){

            System.out.println("[!]  NO HAY RESERVA DE VIAJE REGISTRADAS");

        }  else {

            System.out.println("[*]  INGRESE EL ID DEL RESERVA DE VIAJE A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<TripBooking> tripBooking = tripBookingService.getTripBookingById(findId);
            tripBooking.ifPresentOrElse(
            t -> {
                System.out.println("  [*]  ID: "+ t.getId() + "\n  [*]  FECHA DE RESERVA: " + t.getBookingDate() + "\n  [*]  ID DE VIAJE: " + t.getIdTrip() + "\n  [*]  ID DE ESTADO DE RESERVA: " + t.getBookingDate());

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
                    String updIdTrip = sc.nextLine();

                    System.out.println("\n[*]  INGRESE EL ID DEL ESTADO DE RESERVA: ");
                    String updIdStatus = sc.nextLine();

                      
                    TripBooking tripsBooking = new TripBooking(findId, fechaReserva, updIdTrip, updIdStatus);
                    tripBookingService.updateTripBooking(tripsBooking);
            },
            () -> System.out.println("[!]  RESERVA DE VIAJE NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
        }
    }


    public void deleteTripBooking(){
        ConsoleUtils.limpiarConsola();
        List<TripBooking> tripBookings = tripBookingService.getAllTripBookings();
        if(tripBookings.isEmpty()){
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

                }
            );
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
                System.out.println(MessageFormat.format("[*] ID : {0}\n[*] FECHA DE RESERVA : {1}\n[*] ID DE VIAJE: {2}\n[*] ID DE ESTADO DE RESERVA: {3}", t.getId(), t.getBookingDate() + t.getIdTrip(), t.getIdBookingStatus()));
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}