package com.vuelos_globales.PlaneModels.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.ConsoleUtils;
import com.vuelos_globales.PlaneModels.application.PlaneModelsService;
import com.vuelos_globales.PlaneModels.domain.PlaneModels;

public class PlaneModelsConsoleController {
    Scanner sc = new Scanner(System.in);

    private final PlaneModelsService planeModelsService;

    public PlaneModelsConsoleController(PlaneModelsService planeModelsService){
        this.planeModelsService = planeModelsService;
    }

    public void createModels(){
        String option = "S";

        while(option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DEL MODELO");
            String newId = sc.nextLine();

            Optional<PlaneModels> model = planeModelsService.findById(newId);
            model.ifPresentOrElse(
                g ->  {
                    System.out.println("[!]  MODELO YA EXISTENTE");
                    System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    sc.nextLine();
                },
                ()-> {
                    System.out.println("\n[*]  INGRESE EL NOMBRE DEL MODELO");
                    String newModel = sc.nextLine();

                    System.out.println("\n[*]  INGRESE EL ID DEL FABRICANTE CORRESPONDIENTE");
                    String newIdManufacturer = sc.nextLine();


                    PlaneModels planeModels = new PlaneModels(newId, newModel, newIdManufacturer);
                    planeModelsService.createPlaneModels(planeModels);
                }
            );
            System.out.println("[*]  DESEA CREAR OTRO MODELO? [S] SI | [CUALQUIER TECLA] NO");
            option = sc.nextLine();   
        }  
    }

    public void searchModels(){
        ConsoleUtils.limpiarConsola();
        List<PlaneModels> modelList = planeModelsService.findAll();

        if(modelList.isEmpty()){
            System.out.println("[!]  NO HAY MODELOS REGISTRADOS");
        }else {
            System.out.println("[*]  INGRESE EL ID DEL MODELO\n\n");
            String findId = sc.nextLine();

            Optional<PlaneModels> planeModels = planeModelsService.findById(findId);
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
        List<PlaneModels> modelList = planeModelsService.findAll();

        if(modelList.isEmpty()){

            System.out.println("[!]  NO HAY MODELOS REGISTRADOS");

        }  else {

            System.out.println("[*]  INGRESE EL ID DEL MODELO A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<PlaneModels> model = planeModelsService.findById(findId);
            model.ifPresentOrElse(
            f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  MODELO: " + f.getModel() + "\n  [*]  ID DEL FABRICANTE: " + f.getIdManufacturer());

                String updateId = f.getId();

                System.out.println("[*]  INGRESE EL NUEVO NOMBRE DEL MODELO");
                String updateModel = sc.nextLine();

                System.out.println("[*]  INGRESE EL NUEVO ID DEL FABRICANTE CORRESPONDIENTE");
                String updateIdManufacturer = sc.nextLine();

                PlaneModels updatePlaneModels = new PlaneModels (updateModel, updateIdManufacturer,updateId);
                planeModelsService.updatePlaneModels(updatePlaneModels);
            },
            () -> System.out.println("[!]  MODELO NO ENCONTRADO")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
        }
    }

    public void deletePlaneModels(){
        ConsoleUtils.limpiarConsola();
        List<PlaneModels> models = planeModelsService.findAll();
        if(models.isEmpty()){
            System.out.println("[!]  NO HAY MODELOS REGISTRADOS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DEL MODELO A ELIMINAR\n\n");
            String findId = sc.nextLine();

            Optional<PlaneModels> planeModels = planeModelsService.findById(findId);
            planeModels.ifPresentOrElse(
                f -> planeModelsService.deletePlaneModels(findId),
                () -> System.out.println("[!]  MODELO NO ENCONTRADO")
            );
        } 
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllPlaneModels(){
        ConsoleUtils.limpiarConsola();
        List<PlaneModels> planeModels = planeModelsService.findAll();
        if(planeModels.isEmpty()){
            System.out.println("[!]  NO HAY COMPUERTAS REGISTRADAS");
        } else {
            planeModelsService.findAll().forEach(f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  MODELO: " + f.getModel() + "\n  [*]  ID DEL FABRICANTE: " + f.getIdManufacturer());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
