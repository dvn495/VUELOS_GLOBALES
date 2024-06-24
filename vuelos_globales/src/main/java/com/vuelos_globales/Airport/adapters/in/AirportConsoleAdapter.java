package com.vuelos_globales.Airport.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.Optional;

import com.vuelos_globales.Airport.application.AirportService;
import com.vuelos_globales.Airport.domain.Airport;

public class AirportConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final AirportService airportService; 

    public AirportConsoleAdapter(AirportService airportService) {
        this.airportService = airportService;
    }

    public void createAirport() {
        System.out.println("[*] INGRESE EL ID DEL AEREOPUERTO A CREAR: ");
        String id = sc.nextLine();

        System.out.println("[*] INGRESE EL NOMBRE DEL AEREOPUERTO: ");
        String airportName = sc.nextLine();

        System.out.println("[*] INGRESE EL ID DE LA CIUDAD DEL AEREOPUERTO: ");
        String airportCity = sc.nextLine();
    
        Airport newAirport = new Airport(id, airportName, airportCity);
        airportService.createAirport(newAirport);
    }

    public void searchAirport() {
        System.out.println("[?] INGRESE EL ID DEL AEREOPUERTO: ");
        String searchId = sc.nextLine();

        Optional<Airport> airport = airportService.getAirportById(searchId);
        airport.ifPresentOrElse(
            a -> System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity())),
            () -> System.out.println("[!] AEREOPUERTO NO ENCONTRADO.")
            );
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
    }

    public void updateAirport() {
        System.out.println("[?] INGRESE EL ID DEL AEREOPUERTO: ");
        String findId = sc.nextLine();

        Optional<Airport> airport = airportService.getAirportById(findId);
        airport.ifPresentOrElse(a -> {
            System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity()));
        },
        () -> System.out.println("[!] AEREOPUERTO NO ENCONTRADO.")
        );
    System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
    sc.nextLine();   
    }

    public void deleteAirline() {
        System.out.println("[?]  INGRESE EL ID DEL AEREOPUERTO: ");
        String findId = sc.nextLine();

        Optional<Airport> airport = airportService.getAirportById(findId);
        airport.ifPresentOrElse(
            a -> {
                airportService.deleteAirport(findId);
                System.out.println("[*] AEREOPUERTO ELIMINADO CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                System.out.println("[!] AEREOPUERTO NO ENCONTRADO.");
            }
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

}
