package com.vuelos_globales.entities.Manufactures.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.Manufactures.application.ManufacturesService;
import com.vuelos_globales.entities.Manufactures.domain.Manufactures;
import com.vuelos_globales.modules.ConsoleUtils;

public class ManufacturesConsoleController {
Scanner scanner = new Scanner(System.in);

    private final ManufacturesService manufacturesService;

    public ManufacturesConsoleController(ManufacturesService manufacturesService) {
        this.manufacturesService = manufacturesService;
    }

    public void createManufacture(){
        String option = "S";

        while(option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DEL FABRICANTE");
            String newId =  scanner.nextLine();
            Optional<Manufactures> Manufacture = manufacturesService.getManufacturerById(newId);
            Manufacture.ifPresentOrElse(
                a -> {
                    System.out.println("[!]  FABRICANTE YA EXISTENTE");
                    System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    scanner.nextLine();
                },
                () -> {
                    System.out.println("[*]  INGRESE EL NOMBRE DEL FABRICANTE A CREAR");
                    String nameManufacture =  scanner.nextLine();
            
                    Manufactures newManufacture = new Manufactures(newId, nameManufacture);
                    manufacturesService.createManufacturer(newManufacture);
                }
            );
            System.out.println("[*]  DESEA REGISTRAR OTRO FABRICANTE? [S] SI | [CUALQUIER TECLA] NO");
            option = scanner.nextLine();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        }
    }

    public void searchManufacture(){
        ConsoleUtils.limpiarConsola();
        List<Manufactures> allManufactures = manufacturesService.getAllManufactures();
        if(allManufactures.isEmpty()){
            System.out.println("[!]  NO HAY FABRICANTES REGISTRADOS");
        }   else {
            System.out.println("[?]  INGRESE EL ID DE EL FABRICANTE A BUSCAR\n\n");
            String findId = scanner.nextLine();
            
            Optional<Manufactures> Manufacture = manufacturesService.getManufacturerById(findId);
            Manufacture.ifPresentOrElse(
                a -> System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getManufacturer()),
                () -> System.out.println("[!]  FABRICANTE NO ENCONTRADO")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void updateManufacture(){
        ConsoleUtils.limpiarConsola();
        List<Manufactures> allManufactures = manufacturesService.getAllManufactures();
        if(allManufactures.isEmpty()){
            System.out.println("[!]  NO HAY FABRICANTES REGISTRADOS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DEL FABRICANTE A EDITAR\n\n");
            String findId = scanner.nextLine();

            Optional<Manufactures> Manufacture = manufacturesService.getManufacturerById(findId);
            Manufacture.ifPresentOrElse(
                a -> {
                    System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getManufacturer());

                    String updateId = a.getId();

                    System.out.println("[*]  INGRESE EL NUEVO NOMBRE DEL FABRICANTE");
                    String updateName = scanner.nextLine();

                    Manufactures updatedManufacture = new Manufactures(updateId, updateName);
                    manufacturesService.updateManufacturer(updatedManufacture);
                },
                () -> System.out.println("[!]  AEROLINEA NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void deleteManufacture(){
        ConsoleUtils.limpiarConsola();
        List<Manufactures> allManufactures = manufacturesService.getAllManufactures();
        if(allManufactures.isEmpty()){
            System.out.println("[!]  NO HAY FABRICANTES REGISTRADOS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DEL FABRICANTE A ELIMINAR\n\n");
            String findId = scanner.nextLine();

            Optional<Manufactures> Manufacture = manufacturesService.getManufacturerById(findId);
            Manufacture.ifPresentOrElse(
                a -> {
                    manufacturesService.deleteManufacturer(findId);
                },
                () -> System.out.println("[!]  FABRICANTE NO ENCONTRADO")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }

    public void getAllManufactures(){
        ConsoleUtils.limpiarConsola();
        List<Manufactures> allManufactures = manufacturesService.getAllManufactures();
        if(allManufactures.isEmpty()){
            System.out.println("[!]  NO HAY FABRICANTES REGISTRADOS");
        }   else {
            System.out.println("[*]  FABRICANTES REGISTRADOS");
            manufacturesService.getAllManufactures().forEach(a -> {
                System.out.println("[*]  ID: "+ a.getId() + "NOMBRE: " + a.getManufacturer());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        scanner.nextLine();
    }
}
