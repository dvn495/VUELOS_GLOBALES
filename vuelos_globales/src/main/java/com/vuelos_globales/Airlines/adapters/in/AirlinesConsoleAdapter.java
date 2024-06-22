package com.vuelos_globales.Airlines.adapters.in;

import java.util.Scanner;
import java.util.Optional;

import com.vuelos_globales.Airlines.application.AirlinesService;
import com.vuelos_globales.Airlines.domain.Airlines;

public class AirlinesConsoleAdapter {
    Scanner scanner = new Scanner(System.in);

    private final AirlinesService airlineService;

    public AirlinesConsoleAdapter(AirlinesService airlineService) {
        this.airlineService = airlineService;
    }

    public void createAirline(){
        System.out.println("[*]  INGRESE EL NOMBRE DE LA AIROLINEA A CREAR");
        String nameAirline =  scanner.nextLine();

        Airlines newAirline = new Airlines(nameAirline);
        airlineService.createAirline(newAirline);
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
                System.out.println("[*]  INGRESE EL NUEVO ID DE LA AIROLINEA");
                String updateId = scanner.nextLine();

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
        airlineService.getAllAirlines().forEach(a -> {
            System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getName());
        });
    }
}
