package com.vuelos_globales.modules.menus;

import java.text.MessageFormat;
import java.util.Scanner;

import com.vuelos_globales.entities.Airlines.adapters.out.AirlinesMySQLRepository;
import com.vuelos_globales.entities.Airport.adapters.in.AirportConsoleAdapter;
import com.vuelos_globales.entities.Airport.adapters.out.AirportMySQLRepository;
import com.vuelos_globales.entities.Airport.application.AirportService;
import com.vuelos_globales.entities.BookingStatus.adapters.out.BookingStatusMySQLRepository;
import com.vuelos_globales.entities.Customer.adapters.out.CustomerMySQLRepository;
import com.vuelos_globales.entities.Employee.adapters.out.EmployeeMySQLRepository;
import com.vuelos_globales.entities.FlightConnection.adapters.in.FlightConnectionConsoleAdapter;
import com.vuelos_globales.entities.FlightConnection.adapters.out.FlightConnectionMySQLRepository;
import com.vuelos_globales.entities.FlightConnection.application.FlightConnectionService;
import com.vuelos_globales.entities.Manufactures.adapters.out.ManufacturesMySQLRepository;
import com.vuelos_globales.entities.PlaneModels.adapters.out.PlaneModelsMySQLRepository;
import com.vuelos_globales.entities.Planes.adapters.in.PlanesConsoleController;
import com.vuelos_globales.entities.Planes.adapters.out.PlanesMySQLRepository;
import com.vuelos_globales.entities.Planes.application.PlanesService;
import com.vuelos_globales.entities.Statuses.adapters.out.StatusMySQLRepository;
import com.vuelos_globales.entities.Trip.adapters.in.TripConsoleAdapter;
import com.vuelos_globales.entities.Trip.adapters.out.TripMySQLRepository;
import com.vuelos_globales.entities.Trip.application.TripService;
import com.vuelos_globales.entities.TripBooking.adapters.out.TripBookingMySQLRepository;
import com.vuelos_globales.entities.TripCrew.adapters.in.TripCrewConsoleAdapter;
import com.vuelos_globales.entities.TripCrew.adapters.out.TripCrewMySQLRepository;
import com.vuelos_globales.entities.TripCrew.application.TripCrewService;
import com.vuelos_globales.entities.TripulationRoles.adapters.out.TripulationRoleMySQLRepository;
import com.vuelos_globales.modules.ConsoleUtils;


public class AdministratorMenu {
    public static void menu(){
        ConsoleUtils.limpiarConsola();

        String banner = "----------MENU ADMINISTRADOR----------";
  
        String[] administratorOpc = {"REGISTRAR AVION", "ASIGNAR TRIPULACION A TRAYECTO","CONSULTAR INFORMACION DE AVION","CONSULTAR INFORMACION DE TRAYECTO", "REGISTRAR AEROPUERTO",
        "CONSULTAR INFORMACION DE UN AEROPUERTO","ACTUALIZAR INFORMACION DE AVION","ELIMINAR AVION","ASIGNAR AERONAVE A TRAYECTO","ACTUALIZAR INFORMACION DE TRAYECTO","ELIMINAR TRAYECTO",
        "ACTUALIZAR INFORMACION DE AEROPUERTO","ELIMINAR AEROPUERTO", "CONSULTAR INFORMACION DE VUELO ","CONSULTAR ASIGNACION DE TRIPULACION","CONSULTAR ESCALAS DE UN PROYECTO",
        "ACTUALIZAR INFORMACION DE ESCALA","ELIMINAR ESCALA","REGISTRAR TARIFA DE VUELO","ACTUALIZAR INFORMACION DE TARIFA DE VUELO","ELIMINAR TARIFA DE VUELO","CONSULTAR TARIFA DE VUELO", 
        "REGISTRAR TIPO DE DOCUMENTO","ACTUALIZAR TIPO DE DOCUMENTO","ELIMINAR TIPO DE DOCUMENTO ", "CONSULTAR TIPO DE DOCUMENTO","VOLVER"};

        System.out.println(banner);

        int i = 1;
        for (String opc : administratorOpc) {
            System.out.println(MessageFormat.format("    [{0}]. {1}", i++, opc));
        }
    
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/airport_database";
        String username = "campus2023";
        String password = "campus2023";

        //MySQL REPOSITORIES

        CustomerMySQLRepository customerMySQLRepository = new CustomerMySQLRepository(url, username, password);
        AirlinesMySQLRepository airlinesMySQLRepository = new AirlinesMySQLRepository(url, username, password);
        TripulationRoleMySQLRepository tripulationRoleMySQLRepository = new TripulationRoleMySQLRepository(url, username, password);
        TripBookingMySQLRepository tripBookingMySQLRepository = new TripBookingMySQLRepository(url, username, password);
        BookingStatusMySQLRepository bookingStatusMySQLRepository = new BookingStatusMySQLRepository(url, username, password);
        TripMySQLRepository tripMySQLRepository = new TripMySQLRepository(url, username, password);
        AirportMySQLRepository airporMySQLRepository = new AirportMySQLRepository(url, username, password);
        EmployeeMySQLRepository employeeMySQLRepository = new EmployeeMySQLRepository(url, username, password);
        FlightConnectionMySQLRepository flightConnectionMySQLRepository = new FlightConnectionMySQLRepository(url, username, password);
        TripCrewMySQLRepository tripCrewMySQLRepository = new TripCrewMySQLRepository(url, username, password);
        ManufacturesMySQLRepository manufacturesMySQLRepository = new ManufacturesMySQLRepository(url, username, password);
        StatusMySQLRepository statusMySQLRepository = new StatusMySQLRepository(url, username, password); 
        PlanesMySQLRepository planesMySQLRepository = new PlanesMySQLRepository(url, username, password);
        PlaneModelsMySQLRepository planesModelsMySQLRepository = new PlaneModelsMySQLRepository(url, username, password);

        boolean isActiveAdministrator = true;

        while (isActiveAdministrator) {
            try {
                int opcMenu = Integer.parseInt(sc.nextLine().trim());

                switch (opcMenu) {
                    case 1 -> {
                        PlanesService planesService = new PlanesService(planesMySQLRepository, planesModelsMySQLRepository, statusMySQLRepository, manufacturesMySQLRepository);
                        PlanesConsoleController planesConsoleController = new PlanesConsoleController(planesService);
                        planesConsoleController.createPlanes();
                    }

                    case 2 -> {
                        TripCrewService tripCrewService = new TripCrewService(tripCrewMySQLRepository, employeeMySQLRepository, flightConnectionMySQLRepository, 
                        tripulationRoleMySQLRepository, airlinesMySQLRepository, airporMySQLRepository, planesMySQLRepository, tripMySQLRepository);
                        TripCrewConsoleAdapter tripCrewConsoleController = new TripCrewConsoleAdapter(tripCrewService);
                        tripCrewConsoleController.createTripCrew();
                    }
                    
                    case 3 -> {
                        PlanesService planesService = new PlanesService(planesMySQLRepository, planesModelsMySQLRepository, statusMySQLRepository, manufacturesMySQLRepository);
                        PlanesConsoleController planesConsoleController = new PlanesConsoleController(planesService);
                        planesConsoleController.searchPlane();
                    }

                    case 4 -> {
                        FlightConnectionService flightConenctionService = new FlightConnectionService(flightConnectionMySQLRepository);
                        FlightConnectionConsoleAdapter flightConnectionConsoleAdapter = new FlightConnectionConsoleAdapter(flightConenctionService);
                        flightConnectionConsoleAdapter.getFlightConnectionByTrip();
                    }

                    case 5 -> {
                        //REGISTRAR AEROPUERTO
                    }

                    case 6 -> {
                        AirportService airportService = new AirportService(airporMySQLRepository);
                        AirportConsoleAdapter airportConsoleAdapter = new AirportConsoleAdapter(airportService);
                        airportConsoleAdapter.searchAirport();
                    }

                    case 7 -> {
                        //ACTUALIZAR INFORMACION DE AVION
                    }

                    case 8 -> {
                        //ELIMINAR UN AVION
                    }

                    case 9 -> {
                        // ASIGNAR AERONAVE A TRAYECTO
                    }

                    case 10 -> {
                        //ACTUALIZAR INFORMACION DE TRAYECTO
                    }

                    case 11 -> {
                        //ELIMINAR TRAYECTO
                    }

                    case 12 -> {
                        //ACTUALIZAR INFORMACION DE AEROPUERTO
                    }

                    case 13 -> {
                        //ELIMINAR AEROPUERO
                    }

                    case 14 -> {
                        TripService tripService = new TripService(tripMySQLRepository, bookingStatusMySQLRepository, tripBookingMySQLRepository, airporMySQLRepository, customerMySQLRepository);
                        TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);
                        tripConsoleAdapter.searchTrip();

                    }

                    case 15 -> {
                        TripCrewService tripCrewService = new TripCrewService(tripCrewMySQLRepository, employeeMySQLRepository, flightConnectionMySQLRepository, 
                        tripulationRoleMySQLRepository, airlinesMySQLRepository, airporMySQLRepository, planesMySQLRepository, tripMySQLRepository);
                        TripCrewConsoleAdapter tripCrewConsoleController = new TripCrewConsoleAdapter(tripCrewService);
                        tripCrewConsoleController.searchTripCrewByTrip();
                    }

                    case 16 -> {
                        //CONSULTAR ESCALAS DE UN PROYECTO
                    }

                    case 17 -> {
                        //ACTUALIZAR INFORMACION DE ESCALA
                    }

                    case 18 -> {
                        //ELIMINAR ESCALA
                    }

                    case 19 -> {
                        //REGISTRAR TARIFA DE VUELO
                    }

                    case 20 -> {
                        //ACTUALIZAR INFORMACION DE TARIFA DE VUELO
                    }
                    
                    case 21 -> {
                        //ELIMINAR TARIFA DE VUELO
                    }

                    case 22 -> {
                        //CONSULTAR TARIFA DE VUELO
                    }

                    case 23 -> {
                        //REGISTRAR TIPO DE DOCUMENTO
                    }

                    case 24 -> {
                        //ACTUALIZAR TIPO DE DOCUMENTO
                    }

                    case 25 -> {
                        //ELIMINAR TIPO DE DOCUMENTO
                    }

                    case 26 -> {
                        //CONSULTAR TIPO DE DOCUMENTO
                    }

                    case 27 -> {
                        isActiveAdministrator = false;
                    }
                        
                    default -> {
                        System.out.println("[!]  Ingresaste una opción inválida.");
                        sc.nextLine();
                    }
                }

            } catch (NumberFormatException e) {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] Ingresaste una opción inválida.");
                sc.nextLine();
            }
        }
    }
}
