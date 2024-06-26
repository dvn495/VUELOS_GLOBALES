package com.vuelos_globales.entities.FlightConnection.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;
import com.vuelos_globales.entities.FlightConnection.application.FlightConnectionService;
import com.vuelos_globales.modules.ConsoleUtils;

public class FlightConnectionConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    public final FlightConnectionService flightConnectionService;

    public FlightConnectionConsoleAdapter(FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
    }

    public void createFlightConnection() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR TRAYECTO ***************");
            System.out.println("[*] INGRESE EL ID DEL TRAYECTO A CREAR: ");
            String id = sc.nextLine();
            Optional<FlightConnection> flightConnection = flightConnectionService.getFlightConnectionById(id);
            flightConnection.ifPresentOrElse(
                f -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", f.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR TRAYECTO ***************");

                    System.out.println("[*] INGRESE EL ORDEN DEL TRAMO: ");
                    int flightConnectionOrder = sc.nextInt();
    
                    System.out.println("[*] INGRESE EL ID DEL VIAJE: ");
                    String flightConnectionTrip = sc.nextLine();
    
                    System.out.println("[*] INGRESE ID DEL AVION DEL TRAYECTO: ");
                    String flightConnectionPlane = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL AEREOPUERTO DE SALIDA DEL TRAYECTO: ");
                    String flightConnectionAirplaneA = sc.nextLine();

                    System.out.println("[*] INGRESE EL AEREOPUERTO DE LLEGADA DEL TRAYECTO: ");
                    String flightConnectionAirplaneB = sc.nextLine();
    
                    FlightConnection newFlightConnection = new FlightConnection(id, flightConnectionOrder, flightConnectionTrip, flightConnectionPlane, flightConnectionAirplaneA, flightConnectionAirplaneB);
                    flightConnectionService.createFlightConnection(newFlightConnection);
                });

            System.out.println("[?] DESEA AÑADIR OTRO TRAYECTO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchFlightConnection() {
        List<FlightConnection> flightConnections = flightConnectionService.getAllFlightConnections();
        
        if (flightConnections.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TRAMO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR TRAMO ***************");
            System.out.println("[?] INGRESE EL ID DEL TRAMO A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<FlightConnection> FlightConnection = flightConnectionService.getFlightConnectionById(findId);
            FlightConnection.ifPresentOrElse(
                e -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** TRAMO ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] ORDEN : {1}\n[*] VIAJE : {2}\n[*] AVION : {3}\n[*] AEREOPUERTO SALIDA : {4}\n[*] AEREOPUERTO LLEGADA : {5}",e.getId(), e.getConnectionOrder(), e.getIdTrip(), e.getIdPlane(), e.getIdAirportA(), e.getIdArportB()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] TRAMO NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void updateFlightConnection() {
        List<FlightConnection> flightConnections = flightConnectionService.getAllFlightConnections();

        if (flightConnections.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TRAMO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL TRAMO A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<FlightConnection> FlightConnection = flightConnectionService.getFlightConnectionById(findId);
            FlightConnection.ifPresentOrElse(
            c -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** REGISTRAR TRAYECTO ***************");

                System.out.println("[*] INGRESE EL ORDEN DEL TRAMO: ");
                int flightConnectionOrder = sc.nextInt();

                System.out.println("[*] INGRESE EL ID DEL VIAJE: ");
                String flightConnectionTrip = sc.nextLine();

                System.out.println("[*] INGRESE ID DEL AVION DEL TRAYECTO: ");
                String flightConnectionPlane = sc.nextLine();

                System.out.println("[*] INGRESE EL AEREOPUERTO DE SALIDA DEL TRAYECTO: ");
                String flightConnectionAirplaneA = sc.nextLine();

                System.out.println("[*] INGRESE EL AEREOPUERTO DE LLEGADA DEL TRAYECTO: ");
                String flightConnectionAirplaneB = sc.nextLine();

                FlightConnection newFlightConnection = new FlightConnection(findId, flightConnectionOrder, flightConnectionTrip, flightConnectionPlane, flightConnectionAirplaneA, flightConnectionAirplaneB);
                flightConnectionService.updateFlightConnection(newFlightConnection);

                ConsoleUtils.limpiarConsola();
                System.out.println("[*] TRAMO ACTUALIZADO CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] TRAMO NO ENCONTRADO");
                sc.nextLine();
            }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void deleteFlightConnection() {
        List<FlightConnection> flightConnections = flightConnectionService.getAllFlightConnections();
        
        if (flightConnections.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TRAMO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL TRAMO A ELIMINAR: ");
            String findId = sc.nextLine();

            Optional<FlightConnection> flightConnection = flightConnectionService.getFlightConnectionById(findId);
            flightConnection.ifPresentOrElse(
                c -> {
                    flightConnectionService.deleteFlightConnection(findId);
                    System.out.println("[!] TRAMO ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  TRAMO NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllFlightConnections() {
        List<FlightConnection> flightConnections = flightConnectionService.getAllFlightConnections();
        
        if (flightConnections.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN TRAMO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            flightConnectionService.getAllFlightConnections().forEach(f -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n[*] ORDEN : {1}\n[*] VIAJE : {2}\n[*] AVION : {3}\n[*] AEREOPUERTO SALIDA : {4}\n[*] AEREOPUERTO LLEGADA : {5}",f.getId(), f.getConnectionOrder(), f.getIdTrip(), f.getIdPlane(), f.getIdAirportA(), f.getIdArportB()));
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}
