package com.vuelos_globales.Gates.adapters.in;

import java.util.Optional;
import java.util.Scanner;
import java.util.List;

import com.vuelos_globales.Gates.domain.Gates;
import com.vuelos_globales.Gates.application.GatesService;;

public class GatesConsoleController {
    Scanner sc = new Scanner(System.in);

    private final GatesService gatesService;

    public GatesConsoleController(GatesService gatesService){
        this.gatesService = gatesService;
    }

    public void createGates(){
        System.out.println("[*]  INGRESE EL ID DE LA COMPUERTA");
        String newId = sc.nextLine();

        System.out.println("\n[*]  INGRESE EL NOMBRE DE LA COMPUERTA");
        String newGate = sc.nextLine();

        System.out.println("\n[*]  INGRESE EL ID DEL AEROPUERTO CORRESPONDIENTE");
        String newIdAirport = sc.nextLine();


        Gates gates = new Gates(newId, newGate, newIdAirport);
        gatesService.createGate(gates);
    }

    public void searchGates(){
        System.out.println("[*]  INGRESE EL ID DE LA COMPUERTA\n\n");
        String findId = sc.nextLine();

        Optional<Gates> gates = gatesService.getGateById(findId);
        gates.ifPresentOrElse(
            f -> System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  COMPUERTA: " + f.getGate() + "\n  [*]  ID DEL AEROPUERTO: " + f.getIdAirport()),
            () -> System.out.println("[!]  COMPUERTA NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void deletegates(){
        System.out.println("[*]  INGRESE EL ID DE LA COMPUERTA A ELIMINAR\n\n");
        String findId = sc.nextLine();

        Optional<Gates> gates = gatesService.getGateById(findId);
        gates.ifPresentOrElse(
            f -> gatesService.deleteGates(findId),
            () -> System.out.println("[!]  COMPUERTA NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllGates(){
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
