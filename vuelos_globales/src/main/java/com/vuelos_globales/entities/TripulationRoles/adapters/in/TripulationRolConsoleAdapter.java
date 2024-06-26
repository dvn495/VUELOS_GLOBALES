package com.vuelos_globales.entities.TripulationRoles.adapters.in;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.TripulationRoles.application.TripulationRoleService;
import com.vuelos_globales.entities.TripulationRoles.domain.TripulationRole;
import com.vuelos_globales.modules.ConsoleUtils;

public class TripulationRolConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final TripulationRoleService tripulationRoleService;

    public TripulationRolConsoleAdapter(TripulationRoleService tripulationRoleService) {
        this.tripulationRoleService = tripulationRoleService;
    }


    public void createTripulationRole(){
        String rta = "S";

        while(rta.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** CREAR ROL DE TRIPULACION ***************");
            System.out.println("[*] INGRESE EL ID DEL ROL DE TRIPULACION A CREAR: ");
            String id = sc.nextLine();
            Optional<TripulationRole> tripulationRole = tripulationRoleService.getTripulationRoleById(id);
            tripulationRole.ifPresentOrElse(
                c ->{
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** CREAR ROL DE TRIPULACION ***************");
                    System.out.println("[*] INGRESE EL NOMBRE DEL ROL A CREAR: ");
                    String roleName = sc.nextLine();

                    TripulationRole newTripulationRole = new TripulationRole(id, roleName);
                    tripulationRoleService.createTripulationRole(newTripulationRole);
                });
            System.out.println("[?] DESEA AÑADIR OTRO ROL? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine(); 
        }
    }


    public void searchTripulationRole(){
        List<TripulationRole> tripulationRoles = tripulationRoleService.getAllTripulationRoles();

        if (tripulationRoles.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN ROL DE TRIPULACION REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR ROL DE TRIPULACION ***************");
            System.out.println("[?] INGRESE EL ID DEL ROL DE TRIPULACION A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<TripulationRole> tripulationRole = tripulationRoleService.getTripulationRoleById(findId);
            tripulationRole.ifPresentOrElse(
                t -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** ROL DE TRIPULACION ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE DE ROL: {1}\n", t.getId(), t.getRole()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  ROL NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }   



    public void updateTripulationRole() {
            List<TripulationRole> tripulationRoleList = tripulationRoleService.getAllTripulationRoles();

            if (tripulationRoleList.isEmpty()) {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] NO HAY NINGUN ROL DE TRIPULACION REGISTRADO");
                sc.nextLine();
            } else {
                ConsoleUtils.limpiarConsola();
                System.out.println("[?] INGRESE EL ID DEL ROL DE TRIPULACION A BUSCAR: ");
                String findId = sc.nextLine();

                Optional<TripulationRole> tripulationRole = tripulationRoleService.getTripulationRoleById(findId);
                tripulationRole.ifPresentOrElse(
                t -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("***** ACTUALIZAR ROL DE TRIPULACION *****");
                    System.out.println(MessageFormat.format("[] ID : {0}\n[] ROL : {1}\n[]", t.getId(), t.getRole()));

                    System.out.println("[?] INGRESE EL ROL DE TRIPULACION : ");
                    String updateRole = sc.nextLine();
                
                    TripulationRole updatedTripulationRole = new TripulationRole(t.getId(), updateRole);
                    tripulationRoleService.updateTripulationRole(updatedTripulationRole);

                    ConsoleUtils.limpiarConsola();
                    System.out.println("[*] ROL DE TRIPULACION ACTUALIZADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] ROL DE TRIPULACION NO ENCONTRADO");
                    sc.nextLine();
                }
                );
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
            }
        } 



    public void deleteTripulationRole(){
        List<TripulationRole> tripulationRoles = tripulationRoleService.getAllTripulationRoles();
        
        if (tripulationRoles.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN ROL DE TRIPULACION REGISTRADO");
            sc.nextLine();
        } else {
            System.out.println("[?] INGRESE EL ID DEL ROL DE TRIPULACION A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<TripulationRole> tripulationRole = tripulationRoleService.getTripulationRoleById(findId);
            tripulationRole.ifPresentOrElse(
                t -> {
                    tripulationRoleService.deleteTripulationRole(findId);
                    System.out.println("[!] ROL ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  ROL NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }       
    }


    public void getAllTripulationRoles(){
        List<TripulationRole> tripulationRoles = tripulationRoleService.getAllTripulationRoles();
        
        if(tripulationRoles.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!]  NO HAY ROLES DE TRIPULACION REGISTRADOS");
            sc.nextLine();
        } else {
            tripulationRoleService.getAllTripulationRoles().forEach(t -> {
                System.out.println("  [*]  ID: "+ t.getId() + "\n  [*]  ROL: " + t.getRole());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

}