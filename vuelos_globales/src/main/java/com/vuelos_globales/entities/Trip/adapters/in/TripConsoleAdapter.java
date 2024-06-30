package com.vuelos_globales.entities.Trip.adapters.in;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.vuelos_globales.modules.ConsoleUtils;
import com.vuelos_globales.entities.City.domain.City;
import com.vuelos_globales.entities.Trip.application.TripService;
import com.vuelos_globales.entities.Trip.domain.Trip;


public class TripConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final TripService tripService;



    public TripConsoleAdapter(TripService tripService) {
        this.tripService = tripService;
    }


    public void createTrip(){
        String rta = "S";

        while (rta.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR VIAJE ***************");
            System.out.println("[*] INGRESE EL ID DEL VIAJE A CREAR: ");
            String id = sc.nextLine();
            Optional<Trip> trip = tripService.getTripById(id);
            trip.ifPresentOrElse(
                t -> {
                    System.out.println(MessageFormat.format("[!] EL VIAJE CON ID (0) YA EXISTE.", t.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR VIAJE ***************");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaReserva = null;
                    boolean isActiveDate = false;
                    String newDate = "";

                    while (!isActiveDate) {
                        System.out.println("\n[*] INGRESE LA FECHA DE VIAJE (dd-MM-yyyy)");
                        System.out.print("INGRESE LA FECHA DE LA VIAJE: ");
                        newDate = sc.nextLine();

                        try {
                            fechaReserva = LocalDate.parse(newDate, formatter);
                            isActiveDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                        }
                    }

                    System.out.println("\n[*]  INGRESE EL PRECIO DE VIAJE: ");
                    Double tripPrice = sc.nextDouble();

                    Trip newTrip = new Trip(id, fechaReserva, tripPrice);
                    tripService.createTrip(newTrip);
                    sc.nextLine();
                });
            System.out.println("[?] DESEA AÑADIR OTRO VIAJE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }


    public void searchTrip() {
        List<Trip> trips = tripService.getAllTrips();
    
        if (trips.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN VIAJE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR VIAJE ***************");
            System.out.println("[?] INGRESE EL ID DEL VIAJE A BUSCAR: ");
            String findId = sc.nextLine();
    
            Optional<Trip> trip = tripService.getTripById(findId);
            trip.ifPresentOrElse(
                t -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** VIAJE ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] FECHA DE VIAJE : {1}\n[*] PRECIO DE VIAJE: {2}\n", t.getId(), t.getTripDate(), t.getPrice()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] VIAJE NO ENCONTRADO");
                    sc.nextLine();
                }
            );
            ConsoleUtils.limpiarConsola();
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }


    public void updateTrip(){
        ConsoleUtils.limpiarConsola();
        List<Trip> trips = tripService.getAllTrips();

        if(trips.isEmpty()){

            System.out.println("[!]  NO HAY VIAJE REGISTRADAS");

        }  else {

            System.out.println("[*]  INGRESE EL ID DEL VIAJE A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<Trip> trip = tripService.getTripById(findId);
            trip.ifPresentOrElse(
            t -> {
                System.out.println("  [*]  ID: "+ t.getId() + "\n  [*]  FECHA DE VIAJE: " + t.getTripDate() + "\n  [*]  PRECIO DE VIAJE: " + t.getPrice());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaViaje = null;
                    boolean isActiveDate = false;
                    String newDate = "";

                    while (!isActiveDate) {
                        System.out.println("\n[*] INGRESE LA FECHA DE VIAJE (dd-MM-yyyy)");
                        System.out.print("INGRESE LA FECHA DE VIAJE: ");
                        newDate = sc.nextLine();

                        try {
                            fechaViaje = LocalDate.parse(newDate, formatter);
                            isActiveDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                        }
                    }
                    System.out.println("\n[*]  INGRESE EL PRECIO DEL VIAJE: ");
                    Double updPrice = sc.nextDouble();

                      
                    Trip tripsBooking = new Trip(findId, fechaViaje, updPrice);
                    tripService.updateTrip(tripsBooking);
            },
            () -> System.out.println("[!]  VIAJE NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
        }
    }


    public void deleteTrip(){
        ConsoleUtils.limpiarConsola();
        List<Trip> trips = tripService.getAllTrips();
        if(trips.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN VIAJE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DEL VIAJE A ELIMINAR\n\n");
            String findId = sc.nextLine();


            Optional<Trip> trip = tripService.getTripById(findId);
            trip.ifPresentOrElse(
                c -> {
                    tripService.deleteTrip(findId);
                    System.out.println("[!] VIAJE ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  VIAJE NO ENCONTRADO");

                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
        if (trips.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN VIAJE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            trips.forEach(t -> {
                String formattedDate = t.getTripDate().format(formatter);
                System.out.println(MessageFormat.format("[*] ID : {0}\n[*] FECHA DE VIAJE : {1}\n[*] PRECIO DE VIAJE: {2}\n", t.getId(), formattedDate, t.getPrice()));
            });
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getTripsByParameters() {
        List<Trip> trips = tripService.getAllTrips();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
        if (trips.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN VIAJE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            LocalDate tripDate = null;
            boolean isActiveDate = true;
            String newDate = "";

            while (isActiveDate) {
                ConsoleUtils.limpiarConsola();
                System.out.println("\n[*] INGRESE LA FECHA DEL VIAJE (dd-mm-yyyy)");
                newDate = sc.nextLine();

                try {
                    tripDate = LocalDate.parse(newDate, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("[!] FECHA INVALIDA. FORMATO: (dd-mm-yyyy).");
                    sc.nextLine();
                }
            }

            // MOSTRAR CIUDADES

            boolean isActiveCity = true;
            String idCityA = "";
            String idCityB = "";
            while (isActiveCity) {
                List<City> cities = tripService.getAllCities();
                if (cities.isEmpty()) {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] NO HAY NINGUNA CIUDAD REGISTRADA");
                } else {
                    ConsoleUtils.limpiarConsola();
                    cities.forEach(c -> {
                        System.out.println(MessageFormat.format("       [*] ID : {0} - CIUDAD: {1}", c.getId(), c.getCityName()));
                    });

                    System.out.println("[*] INGRESE EL ID DE LA CIUDAD DE PARTIDA: ");
                    idCityA = sc.nextLine();

                    System.out.println("[*] INGRESE EL ID DE LA CIUDAD DE LLEGADA: ");
                    idCityB = sc.nextLine();
                    if (idCityB.equalsIgnoreCase(idCityA)) {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("[!] LAS CIUDADES DE PARTIDA Y LLEGADA NO PUEDEN SE IGUALES");
                        ConsoleUtils.esperarEntrada();
                    } else {
                        Optional<List<Trip>> matchTrips = tripService.getTripsByParameters(tripDate, idCityA, idCityB);

                        matchTrips.ifPresentOrElse(
                            t -> {
                                System.out.println("[*] VIAJES QUE COINCIDIERON:");
                                ConsoleUtils.limpiarConsola();
                                t.forEach(trip -> {
                                    System.out.println(MessageFormat.format("       [*] ID : {0} - FECHA : {1}", trip.getId(), trip.getTripDate()));
                                });
                                ConsoleUtils.esperarEntrada();
                            }, 
                            () -> {
                                ConsoleUtils.limpiarConsola();
                                System.out.println("[!] NO HAY NINGUN VIAJE QUE CUENTE CON ESTOS PARAMETROS");
                                ConsoleUtils.esperarEntrada();
                            });
                            isActiveCity = false;
                    }
                }
            }

        }
    }
}