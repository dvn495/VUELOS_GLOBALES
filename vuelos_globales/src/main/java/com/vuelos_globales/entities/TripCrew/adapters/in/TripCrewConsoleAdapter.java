package com.vuelos_globales.entities.TripCrew.adapters.in;

import com.vuelos_globales.entities.TripCrew.application.TripCrewService;
import com.vuelos_globales.entities.TripCrew.domain.TripCrew;
import com.vuelos_globales.modules.ConsoleUtils;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TripCrewConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final TripCrewService tripCrewService;


    public TripCrewConsoleAdapter(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }


    public void createTripCrew(){
        String rta = "S";

        while(rta.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** CREAR DE TRIPULACION DE VIAJE ***************");
            System.out.println("[*] INGRESE EL ID DEL DE TRIPULACION DE VIAJE A CREAR: ");
            String id = sc.nextLine();
            Optional<TripCrew> tripCrew = tripCrewService.getTripCrewById(id);
            tripCrew.ifPresentOrElse(
                tc ->{
                    System.out.println(MessageFormat.format("[!] EL ID (0) DE TRIPULACION DE VIAJE YA ESTA OCUPADO.", tc.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** CREAR TRIPULACION DE VIAJE ***************");
                    System.out.println("[*] INGRESE EL ID DE LA CONEXION: ");
                    String idConnect = sc.nextLine();

                    System.out.println("[*] INGRESE EL ID DEL EMPLEADO A CREAR: ");
                    String idEmployee = sc.nextLine();

                    TripCrew newTripCrew = new TripCrew(id, idEmployee, idConnect);
                    tripCrewService.createTripCrew(newTripCrew);
                });
            System.out.println("[?] DESEA AÑADIR OTRA TRIPULACION DE VIAJE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine(); 
        }
    }


    public void searchTripCrew(){
        List<TripCrew> tripCrews = tripCrewService.getAllTripCrews();

        if (tripCrews.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUNA TRIPULACION DE VIAJE REGISTRADA");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR TRIPULACION DE VIAJE ***************");
            System.out.println("[?] INGRESE EL ID DE LA TRIPULACION DE VIAJE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<TripCrew> tripCrew = tripCrewService.getTripCrewById(findId);
            tripCrew.ifPresentOrElse(
                tc -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** TRIPULACION DE VIAJE ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] ID DE EMPLEADO: {1}\n[*] ID DE CONEXION: {2}\n", tc.getId(), tc.getIdEmployee(), tc.getIdConnection()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] TRIPULACION DE VIAJE NO ENCONTRADA");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }   



    public void updateTripCrew() {
            List<TripCrew> tripCrewList = tripCrewService.getAllTripCrews();

            if (tripCrewList.isEmpty()) {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] NO HAY NINGUNA TRIPULACION DE VIAJE REGISTRADA");
                sc.nextLine();
            } else {
                ConsoleUtils.limpiarConsola();
                System.out.println("[?] INGRESE EL ID DE LA TRIPULACION DE VIAJE A BUSCAR: ");
                String findId = sc.nextLine();

                Optional<TripCrew> tripCrew = tripCrewService.getTripCrewById(findId);
                tripCrew.ifPresentOrElse(
                tc -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("***** ACTUALIZAR TRIPULACION DE VIAJE *****");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] ID DE EMPLEADO: {1}\n[*] ID DE CONEXION: {2}\n", tc.getId(), tc.getIdEmployee(), tc.getIdConnection()));

                    System.out.println("[?] INGRESE EL ID DE EMPLEADO : ");
                    String updIdEmployee = sc.nextLine();

                    System.out.println("[?] INGRESE EL ID DE CONEXION : ");
                    String updIdConnect = sc.nextLine();
                
                    TripCrew updatedTripCrew = new TripCrew(findId, updIdEmployee, updIdConnect);
                    tripCrewService.updateTripCrew(updatedTripCrew);

                    ConsoleUtils.limpiarConsola();
                    System.out.println("[*] TRIPULACION DE VIAJE ACTUALIZADA CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] TRIPULACION DE VIAJE NO ENCONTRADA");
                    sc.nextLine();
                }
                );
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
            }
        } 



    public void deleteTripCrew(){
        List<TripCrew> tripCrews = tripCrewService.getAllTripCrews();
        
        if (tripCrews.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUNA TRIPULACION DE VIAJE REGISTRADA");
            sc.nextLine();
        } else {
            System.out.println("[?] INGRESE EL ID DE LA TRIPULACION DE VIAJE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<TripCrew> tripCrew = tripCrewService.getTripCrewById(findId);
            tripCrew.ifPresentOrElse(
                tc -> {
                    tripCrewService.deleteTripCrew(findId);
                    System.out.println("[!] TRIPULACION DE VIAJE ELIMINADA CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  TRIPULACION DE VIAJE NO ENCONTRADA");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }       
    }


    public void getAllTripCrews(){
        List<TripCrew> tripCrews = tripCrewService.getAllTripCrews();
        
        if(tripCrews.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!]  NO HAY TRIPULACION DE VIAJE REGISTRADA");
            sc.nextLine();
        } else {
            tripCrewService.getAllTripCrews().forEach(tc -> {
                System.out.println("  [*]  ID: "+ tc.getId() + "\n  [*] ID DE EMPLEADO : " + tc.getIdEmployee() + "\n  [*] ID DE CONEXION :" + tc.getIdConnection());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}