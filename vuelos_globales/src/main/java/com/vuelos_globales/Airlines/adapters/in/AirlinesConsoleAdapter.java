package com.vuelos_globales.Airlines.adapters.in;

import java.util.Optional;
import java.util.Scanner;
import java.util.List; 

import com.vuelos_globales.Airlines.application.AirlinesService;
import com.vuelos_globales.Airlines.domain.Airlines;

public class AirlinesConsoleAdapter {
    Scanner scanner = new Scanner(System.in);

    private final AirlinesService airlineService;

    public AirlinesConsoleAdapter(AirlinesService airlineService) {
        this.airlineService = airlineService;
    }

    public void createAirline(){
        String option = "S";
        boolean isActive = true;
        String newId = "";

        while(option.equalsIgnoreCase("S")){
            while (isActive) {
                System.out.println("[*]  INGRESE EL ID DE LA AEROLINEA");
                newId =  scanner.nextLine();
                Optional<Airlines> airline = airlineService.getAirlineById(newId);
                airline.ifPresentOrElse(
                    a -> {
                        System.out.println("[!]  AIROLINEA YA EXISTENTE");
                        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                        scanner.nextLine();
                    },
                    () -> 
                        isActive = false
                    
                );
            }
            System.out.println("[*]  INGRESE EL NOMBRE DE LA AIROLINEA A CREAR");
            String nameAirline =  scanner.nextLine();
    
            Airlines newAirline = new Airlines(newId, nameAirline);
            airlineService.createAirline(newAirline);

            System.out.println("[?]  DESEA REGISTRAR OTRA AEROLINEA? [S] si | [CUALQUIER OTRA TECLA] no");
            option = scanner.nextLine();
        }
    }

    public void searchAirline(){
        System.out.println("[?]  INGRESE EL NOMBRE DE LA AIROLINEA A BUSCAR\n\n");
        String findId = scanner.nextLine();
        
        Optional<Airlines> airline = airlineService.getAirlineById(findId);
        airline.ifPresentOrElse(
            a -> System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getName()),
            () -> System.out.println("[!]  AEROLINEA NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void updateAirline(){
        System.out.println("[*]  INGRESE EL ID DE LA AIROLINEA A EDITAR\n\n");
        String findId = scanner.nextLine();

        Optional<Airlines> airline = airlineService.getAirlineById(findId);
        airline.ifPresentOrElse(
            a -> {
                System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getName());
                boolean isActive = true;
                String updateId = "";
                while( isActive ) {
                    System.out.println("[*]  INGRESE EL NUEVO ID DE LA AIROLINEA");
                    updateId = scanner.nextLine();
                    airlineService.getAirlineById(updateId);
                    airline.ifPresentOrElse(
                        c -> {
                            System.out.println("[!]  AIROLINEA YA EXISTENTE");
                            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                            scanner.nextLine();
                        },
                        () -> 
                            isActive = false
                        
                    );
                }

                System.out.println("[*]  INGRESE EL NUEVO NOMBRE DE LA AIROLINEA");
                String updateName = scanner.nextLine();

                Airlines updatedAirline = new Airlines(updateId, updateName);
                airlineService.updateAirline(updatedAirline);
            },
            () -> System.out.println("[!]  AEROLINEA NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void deleteAirline(){
        System.out.println("[*]  INGRESE EL ID DE LA AIROLINEA A ELIMINAR\n\n");
        String findId = scanner.nextLine();

        Optional<Airlines> airline = airlineService.getAirlineById(findId);
        airline.ifPresentOrElse(
            a -> {
                airlineService.deleteAirline(findId);
            },
            () -> System.out.println("[!]  AEROLINEA NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void getAllAirlines(){
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
