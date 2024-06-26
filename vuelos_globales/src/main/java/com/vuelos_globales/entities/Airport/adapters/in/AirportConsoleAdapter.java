package com.vuelos_globales.entities.Airport.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Airport.application.AirportService;
import com.vuelos_globales.entities.Airport.domain.Airport;
import com.vuelos_globales.modules.ConsoleUtils;


public class AirportConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final AirportService airportService; 

    public AirportConsoleAdapter(AirportService airportService) {
        this.airportService = airportService;
    }

    public void createAirport() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR CLIENTE ***************");
            System.out.println("[*] INGRESE EL ID DEL CLIENTE A CREAR: ");
            String id = sc.nextLine();
            Optional<Airport> airport = airportService.getAirportById(id);
            airport.ifPresentOrElse(
                a -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", a.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR AEREOPUERTO ***************");

                    System.out.println("[*] INGRESE EL ID DEL AEREOPUERTO: ");
                    String airportID = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL NOMBRE DEL AEREOPUERTO: ");
                    String airportName = sc.nextLine();
    
                    System.out.println("[*] INGRESE LA CIUDAD DEL AEREOPUERTO: ");
                    String airportCity = sc.nextLine();

    
                    Airport newAirport = new Airport(airportID, airportName, airportCity);
                    airportService.createAirport(newAirport);
                });

            System.out.println("[?] DESEA AÑADIR OTRO CLIENTE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchAirport() {
        List<Airport> airports = airportService.getAllAirports();
        
        if (airports.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR AEREOPUERTO ***************");
            System.out.println("[?] INGRESE EL ID DEL AEREOPUERTO A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Airport> airport = airportService.getAirportById(findId);
            airport.ifPresentOrElse(
                a -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** AEREOPUERTO ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  AEREOPUERTO NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void updateAirport() {
        List<Airport> airports = airportService.getAllAirports();

        if (airports.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Airport> airport = airportService.getAirportById(findId);
            airport.ifPresentOrElse(
            a -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** ACTUALIZAR CLIENTE ***************");
                System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity()));


                System.out.println("[*] INGRESE EL ID DEL AEREOPUERTO: ");
                String airportID = sc.nextLine();

                System.out.println("[*] INGRESE EL NOMBRE DEL AEREOPUERTO: ");
                String airportName = sc.nextLine();

                System.out.println("[*] INGRESE LA CIUDAD DEL AEREOPUERTO: ");
                String airportCity = sc.nextLine();


                Airport newAirport = new Airport(airportID, airportName, airportCity);
                airportService.createAirport(newAirport);

                ConsoleUtils.limpiarConsola();
                System.out.println("[*] AEREOPUERTO ACTUALIZADO CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] AEREOPUERTO NO ENCONTRADO");
                sc.nextLine();
            }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void deleteAirport() {
        List<Airport> airports = airportService.getAllAirports();
        
        if (airports.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN AEREOPUERTO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL AEREOPUERTO A ELIMINAR: ");
            String findId = sc.nextLine();

            Optional<Airport> airport = airportService.getAirportById(findId);
            airport.ifPresentOrElse(
                a -> {
                    airportService.deleteAirport(findId);
                    System.out.println("[!] AEREOPUERTO ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  AEREOPUERTO NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        
        if (airports.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN AEREOPUERTO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            airportService.getAllAirports().forEach(a -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity())); 
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

}
