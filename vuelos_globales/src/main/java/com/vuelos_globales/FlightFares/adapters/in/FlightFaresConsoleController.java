package com.vuelos_globales.FlightFares.adapters.in;

import java.util.Scanner;
import java.util.Optional;

import com.vuelos_globales.FlightFares.application.FlightFaresService;
import com.vuelos_globales.FlightFares.domain.FlightFares;

public class FlightFaresConsoleController {
    Scanner sc = new Scanner(System.in);

    private final FlightFaresService flightFaresService;

    public FlightFaresConsoleController(FlightFaresService flightFaresService){
        this.flightFaresService = flightFaresService;
    }

    public void createFlightFare(){
        System.out.println("[*]  INGRESE EL ID DE LA TARIFA DE VUELO");
        String newId = sc.nextLine();

        System.out.println("\n[*]  INGRESA UNA DESCRIPCION");
        String newDescription = sc.nextLine();

        System.out.println("\n[*]  INGRESE LOS DETALLES");
        String newDetails = sc.nextLine();

        System.out.println("\n[*] INGRESE EL VALOR DE LA TARIFA DE VUELO");
        double newValue = sc.nextDouble();

        FlightFares flightfare = new FlightFares(newId, newDescription, newDetails, newValue);
        flightFaresService.createFlightFare(flightfare);
    }

    public void searchFlightFare(){
        System.out.println("[*]  INGRESE EL ID DE LA TARIFA DE VUELO\n\n");
        String findId = sc.nextLine();

        Optional<FlightFares> flightfare = flightFaresService.getFlightFareById(findId);
        flightfare.ifPresentOrElse(
            f -> System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  DESCRIPCION: " + f.getDescription() + "\n  [*]  DETALLES: " + f.getDetails() + "\n  [*]  VALOR:  " + f.getValue()),
            () -> System.out.println("[!]  TARIFA DE VUELO NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void deleteFlightFare(){
        System.out.println("[*]  INGRESE EL ID DE LA TARIFA DE VUELO A ELIMINAR\n\n");
        String findId = sc.nextLine();

        Optional<FlightFares> flightfare = flightFaresService.getFlightFareById(findId);
        flightfare.ifPresentOrElse(
            f -> flightFaresService.deleteFlighFare(findId),
            () -> System.out.println("[!]  TARIFA DE VUELO NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllFlightFares(){
        flightFaresService.getAllFlightFares().forEach(f -> {
            System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  DESCRIPCION: " + f.getDescription() + "\n  [*]  DETALLES: " + f.getDetails() + "\n  [*]  VALOR:  " + f.getValue());
        });
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
