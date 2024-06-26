package com.vuelos_globales.entities.Planes.adapters.in;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.Manufactures.domain.Manufactures;
import com.vuelos_globales.entities.PlaneModels.domain.PlaneModels;
import com.vuelos_globales.entities.Planes.application.PlanesService;
import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Statuses.domain.Status;
import com.vuelos_globales.modules.ConsoleUtils;

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
            System.out.println("------REGISTRO DE AVION--------");
            System.out.println("\n[*]  INGRESE EL ID DEL AVION");
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
                    String newPlates = sc.nextLine();

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

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaCreacion = null;
                    boolean isActiveDate = false;
                    String newDate = "";

                    while (!isActiveDate) {
                        System.out.println("\n[*] INGRESE LA FECHA DE FABRICACION (dd-MM-yyyy)");
                        newDate = sc.nextLine();

                        try {
                            fechaCreacion = LocalDate.parse(newDate, formatter);
                            isActiveDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                        }
                    }

                    boolean isActiveModels = true;
                    String newIdModel = "";

                    while (isActiveModels) {
                        List<PlaneModels> planeModels = planesService.findAllModels();
                        if(planeModels.isEmpty()){
                            System.out.println("  [!]  NO HAY MODELOS REGISTRADOS\n\n [!] REGISTRE UN MODELO");
                            System.out.println("  [*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                            sc.nextLine();
                            String optionModel = "S";

                            while(optionModel.equalsIgnoreCase("S")){
                                System.out.println("\n  [*]  INGRESE EL ID DEL MODELO");
                                String findModelId = sc.nextLine();

                                Optional<PlaneModels> models = planesService.findByIdModel(findModelId);
                                models.ifPresentOrElse(
                                    
                                    g ->  {

                                        System.out.println("  [!]  MODELO YA EXISTENTE");
                                        System.out.println("   [*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                                        sc.nextLine();
                                    },
                                    ()-> {

                                        System.out.println("\n  [*]  INGRESE EL MODELO");
                                        String newModel = sc.nextLine();

                                        
                                            List<Manufactures> manufactures = planesService.getAllManufactures();
                                            if(manufactures.isEmpty()){
                                                System.out.println("\n    [!]  NO HAY FABRICANTES REGISTRADOS\n\n    [!] REGISTRE UN FABRICANTE");
                                                sc.nextLine();
                                                String optionManufacturer = "S";

                                                while(optionManufacturer.equalsIgnoreCase("S")){
                                                    System.out.println("\n    [*]  INGRESE UN ID PARA EL FABRICANTE");
                                                    String findManufacturerId = sc.nextLine();

                                                    Optional<PlaneModels> manufacturer = planesService.findByIdModel(findManufacturerId);
                                                    manufacturer.ifPresentOrElse(
                                                        g ->  {
                                                            System.out.println("    [!]  FABRICANTE YA EXISTENTE");
                                                            System.out.println("    [*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                                                            sc.nextLine();
                                                        },
                                                        ()-> {
                                                            System.out.println("    [*]  INGRESE EL NOMBRE DEL FABRICANTE A CREAR");
                                                            String nameManufacture =  sc.nextLine();
                                                    
                                                            Manufactures newManufacture = new Manufactures(findManufacturerId, nameManufacture);
                                                            planesService.createManufacturer(newManufacture);
                                                        }
                                                    );
                                                    System.out.println("    [*]  DESEA REGISTRAR OTRO FABRICANTE? [S] SI | [CUALQUIER TECLA] NO");
                                                    optionManufacturer = sc.nextLine();   
                                                }  
                                                
                                            }    
                                        
                                        
                                        planesService.getAllManufactures().forEach(f -> {
                                            System.out.println("  [*]  ID: "+ f.getId() + "NOMBRE: " + f.getManufacturer());
                                        });
                                        System.out.println("\n  [*]  INGRESE EL ID DEL FABRICANTE DEL MODELO");
                                        String newIdManufacturer = sc.nextLine();
                                        
                                        PlaneModels planes = new PlaneModels(findModelId, newModel, newIdManufacturer);
                                        planesService.createPlaneModels(planes);
                                    }
                                );
                                System.out.println("  [*]  DESEA REGISTRAR OTRO MODELO? [S] SI | [CUALQUIER TECLA] NO");
                                optionModel = sc.nextLine();   
                            }  

                        } 
                        planesService.findAllModels().forEach(f -> {
                            System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  MODELO: " + f.getModel() + "\n  [*]  ID DEL FABRICANTE: " + f.getIdManufacturer());
                        });
                        System.out.println("\n[*]  INGRESE EL ID DEL MODELO DEL AVION");
                        newIdModel = sc.nextLine();
                    }

                    boolean isActiveState = true;
                    String newIdStatus = "";
                    while (isActiveState) {
                        List<Status> statuses = planesService.getAllStatuses();
                        if(statuses.isEmpty()){
                            System.out.println("[!]  NO HAY ESTADOS REGISTRADOS\n\n[!] REGISTRE UN ESTADO");
                            sc.nextLine();
                            String optionStatus = "S";

                            while(optionStatus.equalsIgnoreCase("S")){
                                System.out.println("\n[*]  INGRESE EL ID DEL ESTADO");
                                String findStatusId = sc.nextLine();

                                Optional<Status> status = planesService.getStatusById(findStatusId);
                                status.ifPresentOrElse(
                                    g ->  {
                                        System.out.println("[!]  ESTADO YA EXISTENTE");
                                        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                                        sc.nextLine();
                                    },
                                    ()-> {

                                        System.out.println("[*]  INGRESE EL NOMBRE DEL ESTADO: ");
                                        String statusName = sc.nextLine();
                        
                                        Status newStatus = new Status(findStatusId, statusName);
                                        planesService.createStatus(newStatus);
                                        System.out.println("[*] ESTADO CREADO CORRECTAMENTE");
                                    }
                                );
                                System.out.println("[*]  DESEA REGISTRAR OTRO ESTADO? [S] SI | [CUALQUIER TECLA] NO");
                                optionStatus = sc.nextLine();   
                            }  

                        } 
                        planesService.getAllStatuses().forEach(f -> {
                            System.out.println(MessageFormat.format("  [*] ID : {0}\n  [*] ESTADO : {1}", f.getId(), f.getStatus()));
                        });

                        System.out.println("\n[*]  INGRESE EL ID DEL ESTADO PARA EL AVION");
                        newIdStatus = sc.nextLine();
                        isActiveState = false;
                    }
                    
                    Planes planes = new Planes(newId, newPlates, newCapacity, fechaCreacion, newIdModel, newIdStatus);
                    planesService.createPlanes(planes);
                }
            );
            System.out.println("[*]  DESEA REGISTRAR OTRO AVION? [S] SI | [CUALQUIER TECLA] NO");
            option = sc.nextLine();   
        }  
    }

    public void searchPlane(){
        ConsoleUtils.limpiarConsola();
        List<Planes> planesList = planesService.findAll();

        if(planesList.isEmpty()){
            System.out.println("[!]  NO HAY AVIONES REGISTRADOS");
        }else {
            System.out.println("[*]  INGRESE EL ID DEL AVION\n\n");
            String findId = sc.nextLine();

            Optional<Planes> planes = planesService.findById(findId);
            planes.ifPresentOrElse(
                f -> System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  PLACAS: " + f.getPlates() + "\n  [*]  CAPACIDAD DEL AVION: " + f.getCapacity() + "\n  [*]  FECHA DE FABRICACION: "+ f.getFabricationDate() + "\n  [*]  ID DEL MODELO" + f.getIdModel() + "\n  [*]  ID DEL ESTADO:" + f.getIdStatus()),
                () -> System.out.println("[!]  AVION NO ENCONTRADO")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void updatePlane(){
        ConsoleUtils.limpiarConsola();
        List<Planes> planeList = planesService.findAll();

        if(planeList.isEmpty()){

            System.out.println("[!]  NO HAY MODELOS REGISTRADOS");

        }  else {

            System.out.println("[*]  INGRESE EL ID DEL MODELO A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<Planes> model = planesService.findById(findId);
            model.ifPresentOrElse(
            f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  PLACAS: " + f.getPlates() + "\n  [*]  CAPACIDAD DEL AVION: " + f.getCapacity() + "\n  [*]  FECHA DE FABRICACION: "+ f.getFabricationDate() + "\n  [*]  ID DEL MODELO" + f.getIdModel() + "\n  [*]  ID DEL ESTADO:" + f.getIdStatus());

                boolean isActive = true;
                    System.out.println("\n[*]  INGRESE LAS PLACAS DEL AVION");
                    String newPlates = sc.nextLine();

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

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate fechaCreacion = null;
                    boolean isActiveDate = false;
                    String newDate = "";

                    while (!isActiveDate) {
                        System.out.println("\n[*] INGRESE LA FECHA DE FABRICACION (dd-MM-yyyy)");
                        System.out.print("Ingrese la fecha de creación: ");
                        newDate = sc.nextLine();

                        try {
                            fechaCreacion = LocalDate.parse(newDate, formatter);
                            isActiveDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                        }
                    }

                    System.out.println("\n[*]  INGRESE EL ID DEL MODELO DEL AVION");
                    String newIdModel = sc.nextLine();

                    System.out.println("\n[*]  INGRESE EL ID DEL ESTADO");
                    String newIdStatus = sc.nextLine();

                      
                    Planes planes = new Planes(findId, newPlates, newCapacity, fechaCreacion, newIdModel, newIdStatus);
                    planesService.createPlanes(planes);
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
            System.out.println("[*]  INGRESE EL ID DEL AVION A ELIMINAR\n\n");
            String findId = sc.nextLine();

            Optional<Planes> planeModels = planesService.findById(findId);
            planeModels.ifPresentOrElse(
                f -> planesService.deletePlanes(findId),
                () -> System.out.println("[!]  AVION NO ENCONTRADO")
            );
        } 
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllPlanes(){
        ConsoleUtils.limpiarConsola();
        List<Planes> plane = planesService.findAll();
        if(plane.isEmpty()){
            System.out.println("[!]  NO HAY AVIONES REGISTRADOS");
        } else {
            planesService.findAll().forEach(f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  PLACAS: " + f.getPlates() + "\n  [*]  CAPACIDAD DEL AVION: " + f.getCapacity() + "\n  [*]  FECHA DE FABRICACION: "+ f.getFabricationDate() + "\n  [*]  ID DEL MODELO" + f.getIdModel() + "\n  [*]  ID DEL ESTADO:" + f.getIdStatus());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    } 
}
