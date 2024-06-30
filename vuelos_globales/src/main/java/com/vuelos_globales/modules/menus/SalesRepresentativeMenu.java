package com.vuelos_globales.modules.menus;

import java.util.Scanner;

import com.vuelos_globales.entities.Airport.adapters.out.AirportMySQLRepository;
import com.vuelos_globales.entities.BookingStatus.adapters.out.BookingStatusMySQLRepository;
import com.vuelos_globales.entities.Customer.adapters.in.CustomerConsoleAdapter;
import com.vuelos_globales.entities.Customer.adapters.out.CustomerMySQLRepository;
import com.vuelos_globales.entities.Customer.application.CustomerService;
import com.vuelos_globales.entities.DocumentType.adapters.in.DocumentTypeConsoleAdapter;
import com.vuelos_globales.entities.DocumentType.adapters.out.DocTypeMySQLRepository;
import com.vuelos_globales.entities.DocumentType.application.DocumentTypeService;
import com.vuelos_globales.entities.FlightConnection.adapters.in.FlightConnectionConsoleAdapter;
import com.vuelos_globales.entities.FlightConnection.adapters.out.FlightConnectionMySQLRepository;
import com.vuelos_globales.entities.FlightConnection.application.FlightConnectionService;
import com.vuelos_globales.entities.FlightFares.adapters.out.FlightFaresMySQLRepository;
import com.vuelos_globales.entities.Trip.adapters.in.TripConsoleAdapter;
import com.vuelos_globales.entities.Trip.adapters.out.TripMySQLRepository;
import com.vuelos_globales.entities.Trip.application.TripService;
import com.vuelos_globales.entities.TripBooking.adapters.in.TripBookingConsoleAdapter;
import com.vuelos_globales.entities.TripBooking.adapters.out.TripBookingMySQLRepository;
import com.vuelos_globales.entities.TripBooking.application.TripBookingService;
import com.vuelos_globales.entities.TripBookingDetails.adapters.out.TripBookingDetailsMySQLRepository;
import com.vuelos_globales.entities.TripCrew.adapters.in.TripCrewConsoleAdapter;
import com.vuelos_globales.entities.TripCrew.adapters.out.TripCrewMySQLRepository;
import com.vuelos_globales.entities.Employee.adapters.out.EmployeeMySQLRepository;
import com.vuelos_globales.entities.TripulationRoles.adapters.out.TripulationRoleMySQLRepository;
import com.vuelos_globales.entities.Airlines.adapters.out.AirlinesMySQLRepository;
import com.vuelos_globales.entities.Planes.adapters.out.PlanesMySQLRepository;
import com.vuelos_globales.entities.TripCrew.application.TripCrewService;
import com.vuelos_globales.modules.ConsoleUtils;

public class SalesRepresentativeMenu {

    public static void menu() {
        ConsoleUtils.limpiarConsola();
        System.out.println("------MENU REPRESENTANTE DE VENTAS ------\n\n");

        String[] salesRepOpc = {"CREAR RESERVA DE VIAJE","CONSULTAR INFORMACION DE CLIENTE","CONSULTAR RESERVA DE VUELO","REGISTRAR CLIENTE","ACTUALIZAR INFORMACION DE CLIENTE","ELIMINAR RESERVA DE VUELO","CONSULTAR INFORMACION DE VUELO","CONSULTAR ASIGNACION DE TRIPULACION","CONSULTAR ESCALAS DE UN TRAYECTO","CONSULTAR TIPO DE DOCUMENTO","VOLVER"};

        int i = 0;

        for (String opc : salesRepOpc) {
            i++;
            System.out.println("       [" + i + "]. " + opc);
        }
    }

    public static void salesRepresentativeMenu() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/airport_database";
        String username = "campus2023";
        String password = "campus2023";


        CustomerMySQLRepository customerMySQLRepository = new CustomerMySQLRepository(url, username, password);
        DocTypeMySQLRepository docTypeMySQLRepository = new DocTypeMySQLRepository(url, username, password);
        TripMySQLRepository tripMySQLRepository = new TripMySQLRepository(url, username, password);
        BookingStatusMySQLRepository bookingStatusMySQLRepository = new BookingStatusMySQLRepository(url, username, password);
        TripBookingMySQLRepository tripBookingMySQLRepository = new TripBookingMySQLRepository(url, username, password);
        TripBookingDetailsMySQLRepository tripBookingDetailsMySQLRepository = new TripBookingDetailsMySQLRepository(url, username, password);
        FlightFaresMySQLRepository flightFaresMySQLRepository = new FlightFaresMySQLRepository(url, username, password);
        AirportMySQLRepository airportMySQLRepository = new AirportMySQLRepository(url, username, password);
        FlightConnectionMySQLRepository flightConnectionMySQLRepository = new FlightConnectionMySQLRepository(url, username, password);
        EmployeeMySQLRepository employeeMySQLRepository = new EmployeeMySQLRepository(url, username, password);
        PlanesMySQLRepository planesMySQLRepository = new PlanesMySQLRepository(url, username, password);
        TripulationRoleMySQLRepository tripulationRoleMySQLRepository = new TripulationRoleMySQLRepository(url, username, password);
        TripCrewMySQLRepository tripCrewMySQLRepository = new TripCrewMySQLRepository(url, username, password);
        AirlinesMySQLRepository airlinesMySQLRepository = new AirlinesMySQLRepository(url, username, password);


        boolean isActiveSalesRep = true;

        while (isActiveSalesRep) {
            ConsoleUtils.limpiarConsola();
            menu();
            try {
                System.out.print("Seleccione una opción: ");
                int opcMenu = Integer.parseInt(sc.nextLine().trim());

                switch (opcMenu) {

                    // CREAR RESERVA DE VIAJE 
                    case 1 -> {
                        TripBookingService tripBookingService = new TripBookingService(tripBookingMySQLRepository, customerMySQLRepository, tripMySQLRepository, bookingStatusMySQLRepository, flightFaresMySQLRepository, tripBookingDetailsMySQLRepository, flightConnectionMySQLRepository);
                        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService);
                        tripBookingConsoleAdapter.createTripBooking();
                    }

                    // CONSULTAR INFORMACION DE CLIENTE
                    case 2 -> {
                        CustomerService customerService = new CustomerService(customerMySQLRepository, docTypeMySQLRepository);
                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                        customerConsoleAdapter.searchCustomer();
                        
                        
                    }

                    //CONSULTAR RESERVA DE VUELO
                    case 3 -> {
                        TripBookingService tripBookingService = new TripBookingService(tripBookingMySQLRepository, customerMySQLRepository, tripMySQLRepository, bookingStatusMySQLRepository, flightFaresMySQLRepository, tripBookingDetailsMySQLRepository, flightConnectionMySQLRepository);
                        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService);
                        tripBookingConsoleAdapter.searchTripBooking();
                    }

                    //REGISTRAR CLIENTE
                    case 4 -> {
                        CustomerService customerService = new CustomerService(customerMySQLRepository, docTypeMySQLRepository);
                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                        customerConsoleAdapter.createCustomer();
                    }

                    //ACTUALIZAR INFORMACION DE CLIENTE
                    case 5 -> {
                        CustomerService customerService = new CustomerService(customerMySQLRepository, docTypeMySQLRepository);
                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                        customerConsoleAdapter.updateCustomer();
                    }

                    //ELIMINAR RESERVA DE VUELO
                    case 6 -> {
                        TripBookingService tripBookingService = new TripBookingService(tripBookingMySQLRepository, customerMySQLRepository, tripMySQLRepository, bookingStatusMySQLRepository, flightFaresMySQLRepository, tripBookingDetailsMySQLRepository, flightConnectionMySQLRepository);
                        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService);
                        tripBookingConsoleAdapter.deleteTripBooking();
                    }

                    //CONSULTAR INFORMACION DE VUELO
                    case 7 -> {
                        TripService tripService = new TripService(tripMySQLRepository, bookingStatusMySQLRepository, tripBookingMySQLRepository, airportMySQLRepository, customerMySQLRepository);
                        TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);
                        tripConsoleAdapter.searchTrip();
                    }

                    //CONSULTAR ASIGNACION DE TRIPULACION
                    case 8 -> {
                        TripCrewService tripCrewService = new TripCrewService(tripCrewMySQLRepository, employeeMySQLRepository, flightConnectionMySQLRepository, tripulationRoleMySQLRepository, airlinesMySQLRepository, airportMySQLRepository, planesMySQLRepository, tripMySQLRepository);
                        TripCrewConsoleAdapter tripCrewConsoleController = new TripCrewConsoleAdapter(tripCrewService);
                        tripCrewConsoleController.searchTripCrewByTrip();
                    }

                    //CONSULTAR ESCALAS DE UN TRAYECTO
                    case 9-> {
                        FlightConnectionService flightConnectionService = new FlightConnectionService(flightConnectionMySQLRepository);
                        FlightConnectionConsoleAdapter flightConnectionConsoleAdapter = new FlightConnectionConsoleAdapter(flightConnectionService);
                        flightConnectionConsoleAdapter.getFlightConnectionByTrip();
                    }

                    //CONSULTAR TIPO DE DOCUMENTO
                    case 10 -> {
                        DocumentTypeService documentTypeService = new DocumentTypeService(docTypeMySQLRepository);
                        DocumentTypeConsoleAdapter documentTypeConsoleAdapter = new DocumentTypeConsoleAdapter(documentTypeService);
                        documentTypeConsoleAdapter.searchDocumentType();
                    }
                    
                    //VOLVER AL MENU DE USUARIOS
                    case 11 -> {
                        isActiveSalesRep = false;
                    }
                    

                    default -> {
                        System.out.println("[!] Ingresaste una opción inválida.");
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


// ESTO ES PARA CREAR UN VIAJE:

// TripService tripService = new TripService(tripMySQLRepository, bookingStatusMySQLRepository, tripBookingMySQLRepository);
// TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);
// tripConsoleAdapter.createTrip();



