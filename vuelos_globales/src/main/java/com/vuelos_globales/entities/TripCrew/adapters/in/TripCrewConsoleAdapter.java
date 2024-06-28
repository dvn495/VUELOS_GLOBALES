package com.vuelos_globales.entities.TripCrew.adapters.in;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelos_globales.entities.Airlines.domain.Airlines;
import com.vuelos_globales.entities.Airport.domain.Airport;
import com.vuelos_globales.entities.Employee.domain.Employee;
import com.vuelos_globales.entities.FlightConnection.domain.FlightConnection;
import com.vuelos_globales.entities.Planes.domain.Planes;
import com.vuelos_globales.entities.Trip.domain.Trip;
import com.vuelos_globales.entities.TripCrew.application.TripCrewService;
import com.vuelos_globales.entities.TripCrew.domain.TripCrew;
import com.vuelos_globales.entities.TripulationRoles.domain.TripulationRole;
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
            System.out.println("[*] INGRESE EL ID DEL DE TRIPULACION DE VIAJE A CREAR [EJEMPLO: TC01]: ");
            String id = sc.nextLine();
            Optional<TripCrew> tripCrew = tripCrewService.getTripCrewById(id);
            tripCrew.ifPresentOrElse(
                tc ->{
                    System.out.println(MessageFormat.format("[!] EL ID {0} DE TRIPULACION DE VIAJE YA ESTA OCUPADO.", tc.getId()));
                },
                () -> {
                    //flight connections

                    System.out.println("*************** CREAR TRIPULACION DE VIAJE ***************");
                    List<FlightConnection> connections = tripCrewService.getAllFlightConnections();
                    if (connections.isEmpty()){
                        System.out.println("|  [!] NO HAY NINGUNA CONEXION REGISTRADA\n|  [*] REGISTRE UNA CONEXION");
                        ConsoleUtils.esperarEntrada();
                        createFlightConnection();
                    }
                    connections.forEach(e -> {
                        System.out.println(MessageFormat.format("\n|  [*] ID DE LA CONEXION: {0}\n|  [*] ORDEN : {1}\n|  [*] VIAJE : {2}\n|  [*] AVION : {3}\n|  [*] AEREOPUERTO SALIDA : {4}\n|  [*] AEREOPUERTO LLEGADA : {5}",e.getId(), e.getConnectionOrder(), e.getIdTrip(), e.getIdPlane(), e.getIdAirportA(), e.getIdArportB()));
                    });
                    System.out.println("[*] INGRESE EL ID DE LA CONEXION: ");
                    String idConnect = sc.nextLine();

                    //employees

                    List<Employee> employees = tripCrewService.getAllEmployees();
                    if (employees.isEmpty()){
                        System.out.println("|  [!] NO HAY NINGUN EMPLEADO REGISTRADO\n|  [*] REGISTRE UN EMPLEADO");
                        ConsoleUtils.esperarEntrada();
                        createEmployee();
                    }
                    employees.forEach( e ->
                        System.out.println(MessageFormat.format("|  [*] ID : {0}\n|  [*] NOMBRE : {1}\n|  [*] APELLIDO : {2}\n|  [*] FECHA DE INGRESO: {3}\n|  [*] ROL : {4}\n|  [*] AEREOLINEA : {5}\n|  [*] AEREOPUERTO : {6}", e.getId(), e.getName() + e.getLastName(), e.getIngressDate(), e.getIdRole(), e.getIdAirline(), e.getIdAirport()))
                    );
                    System.out.println("[*] INGRESE EL ID DEL EMPLEADO A AÑADIR: ");
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

    public void searchTripCrewByTrip(){
        List<TripCrew> tripCrews = tripCrewService.getAllTripCrews();
        
            
        if (tripCrews.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUNA TRIPULACION DE VIAJE REGISTRADA");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR TRIPULACION DE VIAJE ***************");
            System.out.println("[?] INGRESE EL ID DEL VUELO DE VIAJE A BUSCAR: ");
            String findId = sc.nextLine();  


            Optional<TripCrew> tripCrew = tripCrewService.getTripCrewByIdTrip(findId);
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

    // EMPLOYEES

    public void createEmployee() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR EMPLEADO ***************");
            System.out.println("[*] INGRESE EL ID DEL EMPLEADO A CREAR [EJEMPLO: E01]: ");
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
    
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate employeeIngress = null;
                    boolean isActiveDate = false;
                    String newDate = "";
                    while (!isActiveDate) {
                        System.out.println("[*] INGRESE LA FECHA DE INGRESO DEL EMPLEADO: ");
                        newDate = sc.nextLine();
                        try {
                            employeeIngress = LocalDate.parse(newDate, formatter);
                            isActiveDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Fecha ingresada no válida. Use el formato dd-MM-yyyy.");
                        }
                    }

                    String employeeRole = null;
                    List<TripulationRole> tripulationRoles = tripCrewService.getAllTripulationRoles();
                    if(tripulationRoles.isEmpty()){
                        System.out.println("[!]  NO HAY ROLES DE TRIPULACION REGISTRADOS\n[!] EL VALOR SERA ASIGNADO COMO NULL");
                        sc.nextLine();
                        ConsoleUtils.limpiarConsola();
                    } else {
                        tripCrewService.getAllTripulationRoles().forEach(t -> {
                            System.out.println("  [*]  ID: "+ t.getId() + "\n  [*]  ROL: " + t.getRole());
                        });
                        System.out.println("[*] INGRESE EL ROL DEL EMPLEADO: ");
                        employeeRole = sc.nextLine();
                    }
                    
                    String employeeAirline = null;
                    List<Airlines> allAirlines = tripCrewService.getAllAirlines();
                    if(allAirlines.isEmpty()){
                        System.out.println("[!]  NO HAY AEROLINEAS REGISTRADAS\n[!] EL VALOR SERA ASIGNADO COMO NULL");
                        ConsoleUtils.esperarEntrada();
                        ConsoleUtils.limpiarConsola();
                    }   else {
                        System.out.println("[*]  AIROLINEAS REGISTRADAS");
                        tripCrewService.getAllAirlines().forEach(a -> {
                            System.out.println("\n|  [*]  ID: "+ a.getId() + "|  NOMBRE: " + a.getName());
                        });
                        System.out.println("[*] INGRESE LA AEREOLINEA DEL EMPLEADO: ");
                        employeeAirline = sc.nextLine();
                    }
                    

                    String employeeAirport = null;
                    List<Airport> airports = tripCrewService.getAllAirports();
                    if (airports.isEmpty()) {
                        System.out.println("[!] NO HAY NINGUN AEREOPUERTO REGISTRADO\n[!] EL VALOR SERA ASIGNADO COMO NULL");
                        ConsoleUtils.esperarEntrada();
                    } else {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("[*]  AEROPUERTOS REGISTRADOS");
                        tripCrewService.getAllAirports().forEach(a -> {
                        System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity())); 
                        });
                        System.out.println("[*] INGRESE EL AEREOPUERTO DEL EMPLEADO: ");
                        employeeAirport = sc.nextLine();
                    }
    
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
            System.out.println("[*] INGRESE EL ID DEL TRAYECTO A CREAR [EJEMPLO: FC01]: ");
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
    
                    String flightConnectionTrip = null;
                    List<Trip> trips = tripCrewService.getAllTrips();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    if (trips.isEmpty()) {
                        System.out.println("[!] NO HAY NINGUN VIAJE REGISTRADO\n[!] EL VALOR SERA ASIGNADO COMO NULL");
                        sc.nextLine();
                        ConsoleUtils.limpiarConsola();
                    } else {
                        ConsoleUtils.limpiarConsola();
                        trips.forEach(t -> {
                            String formattedDate = t.getTripDate().format(formatter);
                            System.out.println(MessageFormat.format("\n[*] ID : {0}\n[*] FECHA DE VIAJE : {1}\n[*] PRECIO DE VIAJE: {2}\n", t.getId(), formattedDate, t.getPrice()));  
                        });
                        System.out.println("\n[*] INGRESE EL ID DEL VIAJE: ");
                        flightConnectionTrip = sc.nextLine();         
                    }
                    
                    String flightConnectionPlane = null;
                    ConsoleUtils.limpiarConsola();
                    List<Planes> plane = tripCrewService.findAllPlanes();
                    if(plane.isEmpty()){
                        System.out.println("[!]  NO HAY AVIONES REGISTRADOS\n[!] EL VALOR SERA ASIGNADO COMO NULL");
                    } else {
                        tripCrewService.findAllPlanes().forEach(f -> {
                            System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  PLACAS: " + f.getPlates() + "\n  [*]  CAPACIDAD DEL AVION: " + f.getCapacity() + "\n  [*]  FECHA DE FABRICACION: "+ f.getFabricationDate() + "\n  [*]  ID DEL MODELO" + f.getIdModel() + "\n  [*]  ID DEL ESTADO:" + f.getIdStatus());
                        });
                        System.out.println("\n[*] INGRESE ID DEL AVION DEL TRAYECTO: ");
                        flightConnectionPlane = sc.nextLine();
                    }
                    

                    String flightConnectionAirplaneA = null;
                    List<Airport> airports = tripCrewService.getAllAirports();
                    if (airports.isEmpty()) {
                        System.out.println("[!] NO HAY NINGUN AEREOPUERTO REGISTRADO PARA SALIDA\n[!] EL VALOR SERA ASIGNADO COMO NULL");
                        ConsoleUtils.esperarEntrada();
                    } else {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("[*]  AEROPUERTOS REGISTRADOS");
                        tripCrewService.getAllAirports().forEach(a -> {
                        System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity())); 
                        });
                        System.out.println("[*] INGRESE EL AEREOPUERTO DE SALIDA DEL TRAYECTO: ");
                        flightConnectionAirplaneA = sc.nextLine();
                    }

                    String flightConnectionAirplaneB = null;
                    if (airports.isEmpty()) {
                        System.out.println("[!] NO HAY NINGUN AEREOPUERTO REGISTRADO PARA LLEGADA\n[!] EL VALOR SERA ASIGNADO COMO NULL");
                        ConsoleUtils.esperarEntrada();
                    } else {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("[*]  AEROPUERTOS REGISTRADOS");
                        tripCrewService.getAllAirports().forEach(a -> {
                        System.out.println(MessageFormat.format("[*] ID : {0}\n [*] AEROPUERTO : {1}\n[*] CIUDAD : {2}", a.getId(), a.getAirportName(), a.getAirportCity())); 
                        });
                        boolean isActivePlane = true;
                        while (isActivePlane){
                            System.out.println("[*] INGRESE EL AEREOPUERTO DE LLEGADA DEL TRAYECTO: ");
                            flightConnectionAirplaneB = sc.nextLine();
                            if (!flightConnectionAirplaneB.equals(flightConnectionAirplaneA)){
                                isActivePlane = false;
                            }   else {
                                System.out.println("[!] EL AEROPUERTO DE LLEGADA NO PUEDE SER EL MISMO DE SALIDA");
                                ConsoleUtils.esperarEntrada();
                            }
                        }
                    }
    
                    FlightConnection newFlightConnection = new FlightConnection(id, flightConnectionOrder, flightConnectionTrip, flightConnectionPlane, flightConnectionAirplaneA, flightConnectionAirplaneB);
                    tripCrewService.createFlightConnection(newFlightConnection);
                });

            System.out.println("[?] DESEA AÑADIR OTRO TRAYECTO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

}