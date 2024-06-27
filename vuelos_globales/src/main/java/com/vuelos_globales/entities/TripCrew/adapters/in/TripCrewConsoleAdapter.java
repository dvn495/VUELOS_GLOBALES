package com.vuelos_globales.entities.TripCrew.adapters.in;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.Employee.domain.Employee;
import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;
import com.vuelos_globales.entities.TripCrew.application.TripCrewService;
import com.vuelos_globales.entities.TripCrew.domain.TripCrew;
import com.vuelos_globales.modules.ConsoleUtils;

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
                    //flight connections

                    System.out.println("*************** CREAR TRIPULACION DE VIAJE ***************");
                    List<FlightConnection> connections = tripCrewService.getAllFlightConnections();
                    if (connections.isEmpty()){
                        System.out.println("|  [!] NO HAY NINGUNA CONEXION REGISTRADA\n|  [*] REGISTRE UNA CONEXION");
                        sc.nextLine();
                        createFlightConnection();
                        sc.nextLine();
                    }
                    connections.forEach(e -> {
                        System.out.println(MessageFormat.format("|  [*] ID DE LA CONEXION: {0}\n|  [*] ORDEN : {1}\n|  [*] VIAJE : {2}\n|  [*] AVION : {3}\n|  [*] AEREOPUERTO SALIDA : {4}\n|  [*] AEREOPUERTO LLEGADA : {5}",e.getId(), e.getConnectionOrder(), e.getIdTrip(), e.getIdPlane(), e.getIdAirportA(), e.getIdArportB()));
                    });
                    System.out.println("[*] INGRESE EL ID DE LA CONEXION: ");
                    String idConnect = sc.nextLine();

                    //employees

                    List<Employee> employees = tripCrewService.getAllEmployees();
                    if (employees.isEmpty()){
                        System.out.println("|  [!] NO HAY NINGUN EMPLEADO REGISTRADO\n|  [*] REGISTRE UN EMPLEADO");
                        sc.nextLine();
                        createEmployee();
                        sc.nextLine();
                    }
                    employees.forEach( e ->
                        System.out.println(MessageFormat.format("|  [*] ID : {0}\n|  [*] NOMBRE : {1}\n|  [*] APELLIDO : {2}\n|  [*] FECHA DE INGRESO: {3}\n|  [*] ROL : {4}\n|  [*] AEREOLINEA : {5}\n|  [*] AEREOPUERTO : {6}", e.getId(), e.getName() + e.getLastName(), e.getIngressDate(), e.getIdRole(), e.getIdAirline(), e.getIdAirport()))
                    );
                    System.out.println("[*] INGRESE EL ID DEL EMPLEADO A CREAR: ");
                    String idEmployee = sc.nextLine();

                    TripCrew newTripCrew = new TripCrew(id, idEmployee, idConnect);
                    tripCrewService.createTripCrew(newTripCrew);
                });

            System.out.println("[?] DESEA AÑADIR OTRO EMPLEADO A LA TRIPULACION DE VIAJE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
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

    // EMPLOYEES

    public void createEmployee() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR EMPLEADO ***************");
            System.out.println("[*] INGRESE EL ID DEL EMPLEADO A CREAR: ");
            String id = sc.nextLine();
            Optional<Employee> employee = tripCrewService.getEmployeeById(id);
            employee.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR EMPLEADO ***************");

                    System.out.println("[*] INGRESE EL NOMBRE DEL EMPLEADO: ");
                    String employeeName = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL APELLIDO DEL EMPLEADO: ");
                    String employeeLastName = sc.nextLine();
    
                    System.out.println("[*] INGRESE LA FECHA DE INGRESO DEL EMPLEADO: ");
                    String employeeIngress = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL ROL DEL EMPLEADO: ");
                    String employeeRole = sc.nextLine();

                    System.out.println("[*] INGRESE LA AEREOLINEA DEL EMPLEADO: ");
                    String employeeAirline = sc.nextLine();

                    System.out.println("[*] INGRESE EL AEREOPUERTO DEL EMPLEADO: ");
                    String employeeAirport = sc.nextLine();
    
                    Employee newEmployee = new Employee(id, employeeName, employeeLastName, employeeIngress, employeeRole, employeeAirline, employeeAirport);
                    tripCrewService.createEmployee(newEmployee);
                });

            System.out.println("[?] DESEA AÑADIR OTRO EMPLEADO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    // FLIGHT CONECCTION

    public void createFlightConnection() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            System.out.println("*************** REGISTRAR TRAYECTO ***************");
            System.out.println("[*] INGRESE EL ID DEL TRAYECTO A CREAR: ");
            String id = sc.nextLine();
            Optional<FlightConnection> flightConnection = tripCrewService.getFlightConnectionById(id);
            flightConnection.ifPresentOrElse(
                f -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", f.getId()));
                },
                () -> {
                    boolean isActive = true;
                    System.out.println("*************** REGISTRAR TRAYECTO ***************");
                    
                    int flightConnectionOrder = 0;
                    while (isActive){
                        System.out.println("[*] INGRESE EL ORDEN DEL TRAMO: ");
                        try {
                            flightConnectionOrder = (Integer.parseInt(sc.nextLine()));
                            isActive = false;
                        }   catch (NumberFormatException e) {
                            System.out.println("Por favor, ingrese un número válido.\n Presione cualquier tecla para continuar...");
                            sc.nextLine();
                        }  
                    }
    
                    System.out.println("[*] INGRESE EL ID DEL VIAJE: ");
                    String flightConnectionTrip = sc.nextLine();
    
                    
                    System.out.println("[*] INGRESE ID DEL AVION DEL TRAYECTO: ");
                    String flightConnectionPlane = sc.nextLine();

                    
                    System.out.println("[*] INGRESE EL AEREOPUERTO DE SALIDA DEL TRAYECTO: ");
                    String flightConnectionAirplaneA = sc.nextLine();


                    System.out.println("[*] INGRESE EL AEREOPUERTO DE LLEGADA DEL TRAYECTO: ");
                    String flightConnectionAirplaneB = sc.nextLine();

    
                    FlightConnection newFlightConnection = new FlightConnection(id, flightConnectionOrder, flightConnectionTrip, flightConnectionPlane, flightConnectionAirplaneA, flightConnectionAirplaneB);
                    tripCrewService.createFlightConnection(newFlightConnection);
                });

            System.out.println("[?] DESEA AÑADIR OTRO TRAYECTO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

}