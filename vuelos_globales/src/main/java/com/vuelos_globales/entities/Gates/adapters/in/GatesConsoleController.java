package com.vuelos_globales.entities.Gates.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.Gates.application.GatesService;
import com.vuelos_globales.entities.Gates.domain.Gates;
import com.vuelos_globales.modules.ConsoleUtils;

public class GatesConsoleController {
    Scanner sc = new Scanner(System.in);

    private final GatesService gatesService;

    public GatesConsoleController(GatesService gatesService){
        this.gatesService = gatesService;
    }

    public void createGates(){
        String option = "S";

        while(option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DE LA COMPUERTA");
            String newId = sc.nextLine();

            Optional<Gates> gate = gatesService.getGateById(newId);
            gate.ifPresentOrElse(
                g ->  {
                    System.out.println("[!]  AIROLINEA YA EXISTENTE");
                    System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    sc.nextLine();
                },
                ()-> {
                    System.out.println("\n[*]  INGRESE EL NOMBRE DE LA COMPUERTA");
                    String newGate = sc.nextLine();

                    System.out.println("\n[*]  INGRESE EL ID DEL AEROPUERTO CORRESPONDIENTE");
                    String newIdAirport = sc.nextLine();


                    Gates gates = new Gates(newId, newGate, newIdAirport);
                    gatesService.createGate(gates);
                }
            );
            System.out.println("[*]  DESEA CREAR OTRA COMPUERTA? [S] SI | [CUALQUIER TECLA] NO");
            option = sc.nextLine();   
        }  
    }

    public void searchGates(){
        ConsoleUtils.limpiarConsola();
        List<Gates> gateList = gatesService.getAllGates();

        if(gateList.isEmpty()){
            System.out.println("[!]  NO HAY COMPUERTAS REGISTRADAS");
        }else {
            System.out.println("[*]  INGRESE EL ID DE LA COMPUERTA\n\n");
            String findId = sc.nextLine();

            Optional<Gates> gates = gatesService.getGateById(findId);
            gates.ifPresentOrElse(
                f -> System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  COMPUERTA: " + f.getGate() + "\n  [*]  ID DEL AEROPUERTO: " + f.getIdAirport()),
                () -> System.out.println("[!]  COMPUERTA NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void updateGate(){
        ConsoleUtils.limpiarConsola();
        List<Gates> gateList = gatesService.getAllGates();

        if(gateList.isEmpty()){

            System.out.println("[!]  NO HAY COMPUERTAS REGISTRADAS");

        }  else {

            System.out.println("[*]  INGRESE EL ID DE LA COMPUERTA A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<Gates> gate = gatesService.getGateById(findId);
            gate.ifPresentOrElse(
            f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  COMPUERTA: " + f.getGate() + "\n  [*]  ID DEL AEROPUERTO: " + f.getIdAirport());

                String updateId = f.getId();

                System.out.println("[*]  INGRESE EL NUEVO NOMBRE DE LA COMPUERTA");
                String updateName = sc.nextLine();

                System.out.println("[*]  INGRESE EL NUEVO NOMBRE DEL AEROPUERTO CORRESPONDIENTE");
                String updateIdAirport = sc.nextLine();

                Gates updateGates = new Gates (updateId,updateName, updateIdAirport);
                gatesService.updateGate(updateGates);
            },
            () -> System.out.println("[!]  COMPUERTA NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
        }
    }

    public void deletegates(){
        ConsoleUtils.limpiarConsola();
        List<Gates> gate = gatesService.getAllGates();
        if(gate.isEmpty()){
            System.out.println("[!]  NO HAY COMPUERTAS REGISTRADAS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DE LA COMPUERTA A ELIMINAR\n\n");
            String findId = sc.nextLine();

            Optional<Gates> gates = gatesService.getGateById(findId);
            gates.ifPresentOrElse(
                f -> gatesService.deleteGates(findId),
                () -> System.out.println("[!]  COMPUERTA NO ENCONTRADA")
            );
        } 
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllGates(){
        ConsoleUtils.limpiarConsola();
        List<Gates> gates = gatesService.getAllGates();
        if(gates.isEmpty()){
            System.out.println("[!]  NO HAY COMPUERTAS REGISTRADAS");
        } else {
            gatesService.getAllGates().forEach(f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  COMPUERTA: " + f.getGate() + "\n  [*]  ID DEL AEROPUERTO: " + f.getIdAirport());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
