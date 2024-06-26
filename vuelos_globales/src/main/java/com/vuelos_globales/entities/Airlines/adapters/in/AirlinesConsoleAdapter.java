package com.vuelos_globales.entities.Airlines.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.Airlines.application.AirlinesService;
import com.vuelos_globales.entities.Airlines.domain.Airlines;
import com.vuelos_globales.modules.ConsoleUtils;

public class AirlinesConsoleAdapter {
    Scanner scanner = new Scanner(System.in);

    private final AirlinesService airlineService;

    public AirlinesConsoleAdapter(AirlinesService airlineService) {
        this.airlineService = airlineService;
    }

    public void createAirline(){
        String option = "S";

        while(option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DE LA AEROLINEA");
            String newId =  scanner.nextLine();
            Optional<Airlines> airline = airlineService.getAirlineById(newId);
            airline.ifPresentOrElse(
                a -> {
                    System.out.println("[!]  AIROLINEA YA EXISTENTE");
                    System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    scanner.nextLine();
                },
                () -> {
                    System.out.println("[*]  INGRESE EL NOMBRE DE LA AIROLINEA A CREAR");
                    String nameAirline =  scanner.nextLine();
            
                    Airlines newAirline = new Airlines(newId, nameAirline);
                    airlineService.createAirline(newAirline);
                }
            );
            System.out.println("[*]  DESEA CREAR OTRA AIROLINEA? [S] SI | [CUALQUIER TECLA] NO");
            option = scanner.nextLine();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        }
    }

    public void searchAirline(){
        ConsoleUtils.limpiarConsola();
        List<Airlines> airlinesList = airlineService.getAllAirlines();

        if(airlinesList.isEmpty()){
            System.out.println("[!]  NO HAY AIROLINEAS REGISTRADAS");
        }   else {
            System.out.println("[?]  INGRESE EL NOMBRE DE LA AIROLINEA A BUSCAR\n\n");
            String findId = scanner.nextLine();
            
            Optional<Airlines> airline = airlineService.getAirlineById(findId);
            airline.ifPresentOrElse(
                a -> System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getName()),
                () -> System.out.println("[!]  AEROLINEA NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void updateAirline(){
        ConsoleUtils.limpiarConsola();
        List<Airlines> airlinesList = airlineService.getAllAirlines();

        if(airlinesList.isEmpty()){
            System.out.println("[!]  NO HAY AIROLINEAS REGISTRADAS");
        } else {
            System.out.println("[*]  INGRESE EL ID DE LA AIROLINEA A EDITAR\n\n");
            String findId = scanner.nextLine();

            Optional<Airlines> airline = airlineService.getAirlineById(findId);
            airline.ifPresentOrElse(
                a -> {
                    System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getName());

                    String updateId = a.getId();

                    System.out.println("[*]  INGRESE EL NUEVO NOMBRE DE LA AIROLINEA");
                    String updateName = scanner.nextLine();

                    Airlines updatedAirline = new Airlines(updateId, updateName);
                    airlineService.updateAirline(updatedAirline);
                },
                () -> System.out.println("[!]  AEROLINEA NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void deleteAirline(){
        ConsoleUtils.limpiarConsola();
        List<Airlines> airlinesList = airlineService.getAllAirlines();

        if(airlinesList.isEmpty()){
            System.out.println("[!]  NO HAY AIROLINEAS REGISTRADAS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DE LA AIROLINEA A ELIMINAR\n\n");
            String findId = scanner.nextLine();

            Optional<Airlines> airline = airlineService.getAirlineById(findId);
            airline.ifPresentOrElse(
                a -> {
                    airlineService.deleteAirline(findId);
                },
                () -> System.out.println("[!]  AEROLINEA NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void getAllAirlines(){
        ConsoleUtils.limpiarConsola();
        List<Airlines> allAirlines = airlineService.getAllAirlines();
        if(allAirlines.isEmpty()){
            System.out.println("[!]  NO HAY AEROLINEAS REGISTRADAS");
        }   else {
            System.out.println("[*]  AIROLINEAS REGISTRADAS");
            airlineService.getAllAirlines().forEach(a -> {
                System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getName());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
        
    }
}
