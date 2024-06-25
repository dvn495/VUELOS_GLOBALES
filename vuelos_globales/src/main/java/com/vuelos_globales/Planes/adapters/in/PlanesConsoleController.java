package com.vuelos_globales.Planes.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.ConsoleUtils;
import com.vuelos_globales.Planes.application.PlanesService;
import com.vuelos_globales.Planes.domain.Planes;

public class PlanesConsoleController {
    Scanner sc = new Scanner(System.in);

    private final PlanesService planesService;

    public PlanesConsoleController(PlanesService planesservice) {
        this.planesService = planesservice;
    }

    public void createPlanes(){
        String option = "S";

        while(option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DEL AVION");
            String newId = sc.nextLine();

            Optional<Planes> plane = planesService.findById(newId);
            plane.ifPresentOrElse(
                g ->  {
                    System.out.println("[!]  AVION YA EXISTENTE");
                    System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    sc.nextLine();
                },
                ()-> {
                    boolean isActive = true;
                    System.out.println("\n[*]  INGRESE LAS PLACAS DEL AVION");
                    String newplane = sc.nextLine();

                    int newCapacity = 0;

                    while (isActive){
                        System.out.println("\n[*]  INGRESE LA CAPACIDAD DEL AVION (SOLO NUMEROS ENTEROS)");
                        try {
                            newCapacity = (Integer.parseInt(sc.nextLine()));
                            isActive = false;
                        }   catch (NumberFormatException e) {
                            System.out.println("Por favor, ingrese un número válido.\n Presione cualquier tecla para continuar...");
                            sc.nextLine();
                        }  
                        
                    }

                    System.out.println("\n[*]  INGRESE EL MODELO DEL AVION");
                      
                    Planes planes = new Planes(newCapacity, newModel, newIdManufacturer);
                    planesService.createPlaneModels(planes);
                }
            );
            System.out.println("[*]  DESEA REGISTRAR OTRO AVION? [S] SI | [CUALQUIER TECLA] NO");
            option = sc.nextLine();   
        }  
    }

    public void searchModels(){
        ConsoleUtils.limpiarConsola();
        List<Planes> modelList = planesService.findAll();

        if(modelList.isEmpty()){
            System.out.println("[!]  NO HAY MODELOS REGISTRADOS");
        }else {
            System.out.println("[*]  INGRESE EL ID DEL MODELO\n\n");
            String findId = sc.nextLine();

            Optional<Planes> planeModels = planesService.findById(findId);
            planeModels.ifPresentOrElse(
                f -> System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  MODELO: " + f.getModel() + "\n  [*]  ID DEL FABRICANTE: " + f.getIdManufacturer()),
                () -> System.out.println("[!]  MODELO NO ENCONTRADO")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void updateModels(){
        ConsoleUtils.limpiarConsola();
        List<Planes> modelList = planesService.findAll();

        if(modelList.isEmpty()){

            System.out.println("[!]  NO HAY MODELOS REGISTRADOS");

        }  else {

            System.out.println("[*]  INGRESE EL ID DEL MODELO A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<Planes> model = planesService.findById(findId);
            model.ifPresentOrElse(
            f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  MODELO: " + f.getModel() + "\n  [*]  ID DEL FABRICANTE: " + f.getIdManufacturer());

                String updateId = f.getId();

                System.out.println("[*]  INGRESE EL NUEVO NOMBRE DEL MODELO");
                String updateModel = sc.nextLine();

                System.out.println("[*]  INGRESE EL NUEVO ID DEL FABRICANTE CORRESPONDIENTE");
                String updateIdManufacturer = sc.nextLine();

                Planes updatePlaneModels = new Planes (updateModel, updateIdManufacturer,updateId);
                planesService.updatePlaneModels(updatePlaneModels);
            },
            () -> System.out.println("[!]  MODELO NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
        }
    }

    public void deletePlaneModels(){
        ConsoleUtils.limpiarConsola();
        List<Planes> models = planesService.findAll();
        if(models.isEmpty()){
            System.out.println("[!]  NO HAY MODELOS REGISTRADOS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DEL MODELO A ELIMINAR\n\n");
            String findId = sc.nextLine();

            Optional<Planes> planeModels = planesService.findById(findId);
            planeModels.ifPresentOrElse(
                f -> planesService.deletePlaneModels(findId),
                () -> System.out.println("[!]  MODELO NO ENCONTRADO")
            );
        } 
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllPlaneModels(){
        ConsoleUtils.limpiarConsola();
        List<Planes> planeModels = planesService.findAll();
        if(planeModels.isEmpty()){
            System.out.println("[!]  NO HAY COMPUERTAS REGISTRADAS");
        } else {
            planesService.findAll().forEach(f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  MODELO: " + f.getModel() + "\n  [*]  ID DEL FABRICANTE: " + f.getIdManufacturer());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
