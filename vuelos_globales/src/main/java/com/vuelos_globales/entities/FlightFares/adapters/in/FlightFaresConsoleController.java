package com.vuelos_globales.entities.FlightFares.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.FlightFares.application.FlightFaresService;
import com.vuelos_globales.entities.FlightFares.domain.FlightFares;
import com.vuelos_globales.modules.ConsoleUtils;

public class FlightFaresConsoleController {
    Scanner sc = new Scanner(System.in);

    private final FlightFaresService flightFaresService;

    public FlightFaresConsoleController(FlightFaresService flightFaresService){
        this.flightFaresService = flightFaresService;
    }

    public void createFlightFare(){
        String option = "S";
    
        while (option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("[*] INGRESE EL ID DE LA TARIFA DE VUELO");
            String newId = sc.nextLine();
            Optional<FlightFares> flightFare = flightFaresService.getFlightFareById(newId);
            flightFare.ifPresentOrElse(
                f -> {
                    System.out.println("[!] TARIFA DE VUELO YA EXISTENTE");
                    System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    sc.nextLine();
                }, 
                () -> {
                    System.out.println("\n[*] INGRESA UNA DESCRIPCION");
                    String newDescription = sc.nextLine();
    
                    System.out.println("\n[*] INGRESE LOS DETALLES");
                    String newDetails = sc.nextLine();
    
                    System.out.println("\n[*] INGRESE EL VALOR DE LA TARIFA DE VUELO");
                    String valueInput = sc.nextLine();
                    Double newValue = null;
                    try {
                        newValue = Double.parseDouble(valueInput);
                    } catch (NumberFormatException e) {
                        System.out.println("[!] VALOR INVALIDO. INTENTE NUEVAMENTE.");
                        return;
                    }
    
                    FlightFares flightfare = new FlightFares(newId, newDescription, newDetails, newValue);
                    flightFaresService.createFlightFare(flightfare);
                }
            );
            System.out.println("[*] DESEA CREAR OTRA TARIFA DE VUELO? [S] SI | [CUALQUIER TECLA] NO");
            option = sc.nextLine(); 
        }
    }
    

    public void searchFlightFare(){
        ConsoleUtils.limpiarConsola();
        List<FlightFares> flightFaresList = flightFaresService.getAllFlightFares();

        if(flightFaresList.isEmpty()){

            System.out.println("[!] NO HAY TARIFAS DE VUELO REGISTRADAS");
        }   else {

            System.out.println("[*]  INGRESE EL ID DE LA TARIFA DE VUELO\n\n");
            String findId = sc.nextLine();
    
            Optional<FlightFares> flightfare = flightFaresService.getFlightFareById(findId);
            flightfare.ifPresentOrElse(
                f -> System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  DESCRIPCION: " + f.getDescription() + "\n  [*]  DETALLES: " + f.getDetails() + "\n  [*]  VALOR:  " + f.getValue()),
                () -> System.out.println("[!]  TARIFA DE VUELO NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void updateFlightFare(){
        ConsoleUtils.limpiarConsola();
        List<FlightFares> flightFaresList = flightFaresService.getAllFlightFares();

        if(flightFaresList.isEmpty()){
            System.out.println("[!] NO HAY TARIFAS DE VUELO REGISTRADAS");
        }   else {

            System.out.println("[*]  INGRESE EL ID DE LA TARIFA DE VUELO A EDITAR\n\n");
            String findId = sc.nextLine();
    
            Optional<FlightFares> flightFare = flightFaresService.getFlightFareById(findId);
            flightFare.ifPresentOrElse(
                f -> {
                    System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  DESCRIPCION: " + f.getDescription() + "\n  [*]  DETALLES: " + f.getDetails() + "\n  [*]  VALOR:  " + f.getValue());
    
                    String updateId = f.getId();
    
                    System.out.println("[*]  INGRESE LA DESCRIPCION ACTUALIZADA");
                    String updateDescription = sc.nextLine();
    
                    System.out.println("[*]  INGRESE LOS DETALLES ACTUALIZADOS");
                    String updateDetails = sc.nextLine();
    
                    System.out.println("[*]  INGRESE EL NUEVO VALOR"); 
                    Double updateValues = sc.nextDouble();
    
                    FlightFares updatedFlightFare = new FlightFares(updateId, updateDescription, updateDetails, updateValues);
                    flightFaresService.updateFlightFare(updatedFlightFare);
                },
                () -> System.out.println("[!]  TARIFA DE VUELO NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void deleteFlightFare(){
        ConsoleUtils.limpiarConsola();
        List<FlightFares> flightFaresList = flightFaresService.getAllFlightFares();

        if(flightFaresList.isEmpty()){
            System.out.println("[!] NO HAY TARIFAS DE VUELO REGISTRADAS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DE LA TARIFA DE VUELO A ELIMINAR\n\n");
            String findId = sc.nextLine();
    
            Optional<FlightFares> flightfare = flightFaresService.getFlightFareById(findId);
            flightfare.ifPresentOrElse(
                f -> flightFaresService.deleteFlighFare(findId),
                () -> System.out.println("[!]  TARIFA DE VUELO NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllFlightFares(){
        ConsoleUtils.limpiarConsola();
        List<FlightFares> flightFaresList = flightFaresService.getAllFlightFares();

        if(flightFaresList.isEmpty()){
            System.out.println("[!] NO HAY TARIFAS DE VUELO REGISTRADAS");
        }   else {
            flightFaresService.getAllFlightFares().forEach(f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  DESCRIPCION: " + f.getDescription() + "\n  [*]  DETALLES: " + f.getDetails() + "\n  [*]  VALOR:  " + f.getValue());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
