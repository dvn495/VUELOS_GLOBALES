package com.vuelos_globales.modules.menus;

import java.text.MessageFormat;
import java.util.Scanner;

// Servicio

import com.vuelos_globales.entities.Trip.application.TripService;
import com.vuelos_globales.entities.TripBooking.application.TripBookingService;
import com.vuelos_globales.entities.DocumentType.application.DocumentTypeService;

// Console Controler

import com.vuelos_globales.entities.Trip.adapters.in.TripConsoleAdapter;
import com.vuelos_globales.entities.TripBooking.adapters.in.TripBookingConsoleAdapter;

// IMPORTACIONES DE REPOSITORIOS MYSQL

import com.vuelos_globales.entities.Trip.adapters.out.TripMySQLRepository;
import com.vuelos_globales.entities.BookingStatus.adapters.out.BookingStatusMySQLRepository;
import com.vuelos_globales.entities.TripBooking.adapters.out.TripBookingMySQLRepository;
import com.vuelos_globales.entities.Airport.adapters.out.AirportMySQLRepository;
import com.vuelos_globales.entities.Customer.adapters.out.CustomerMySQLRepository;
import com.vuelos_globales.entities.Customer.application.CustomerService;
import com.vuelos_globales.entities.City.adapters.out.CityMySQLRepository;
import com.vuelos_globales.entities.Planes.adapters.out.PlanesMySQLRepository;
import com.vuelos_globales.entities.DocumentType.adapters.out.DocTypeMySQLRepository;

import com.vuelos_globales.modules.ConsoleUtils;


public class CustomerMenu {
    static String url = "jdbc:mysql://localhost:3306/airport_database";
    static String username = "campus2023";
    static String password = "campus2023";
    
    // INICIALIZACION REPOSITORIOS
    static TripMySQLRepository tripMySQLRepository = new TripMySQLRepository(url, username, password);
    static TripBookingMySQLRepository tripBookingMySQLRepository = new TripBookingMySQLRepository(url, username, password);
    static BookingStatusMySQLRepository bookingStatusMySQLRepository = new BookingStatusMySQLRepository(url, username, password);
    static AirportMySQLRepository airportMySQLRepository = new AirportMySQLRepository(url, username, password);
    static CustomerMySQLRepository customerMySQLRepository = new CustomerMySQLRepository(url, username, password);
    static CityMySQLRepository cityMySQLRepository = new CityMySQLRepository(url, username, password);
    static TripBookingMySQLRepository tripBookingMySQLRepository2 = new TripBookingMySQLRepository(url, username, password);
    static PlanesMySQLRepository planesMySQLRepository = new PlanesMySQLRepository(url, username, password);
    static DocTypeMySQLRepository documentTypeMySQLRepository = new DocTypeMySQLRepository(url, username,password);
    
    // IMPORTACION DE SERVICIOS
    static TripService tripService = new TripService(tripMySQLRepository,bookingStatusMySQLRepository,tripBookingMySQLRepository, airportMySQLRepository, customerMySQLRepository, cityMySQLRepository);

    static TripBookingService tripBookingService = new TripBookingService(tripBookingMySQLRepository, tripMySQLRepository, bookingStatusMySQLRepository, customerMySQLRepository, planesMySQLRepository);

    static CustomerService customerService = new CustomerService(customerMySQLRepository, documentTypeMySQLRepository);

    static DocumentTypeService documentTypeService = new DocumentTypeService(documentTypeMySQLRepository);

    // INICIALIZACION CONSOLE CONTROLLERS
    static TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);
    static TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService, customerService);

    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        ConsoleUtils.limpiarConsola();

        System.out.println("******************** MENU CLIENTE ********************");

        String[] customerOpc = {"Administrar Vuelos", "Administrar Reservas", "Pagos","Volver"};

        ConsoleUtils.listarOpc(customerOpc);
    }

    public static void flightsMenu() {
        boolean isActiveFlights = true;

        while (isActiveFlights) {
            ConsoleUtils.limpiarConsola();
            System.out.println("******************** ADMINISTRAR VUELOS ********************");
            String[] flightsOpc = {"Filtrar Vuelo", "Consultar Vuelo", "Seleccionar Vuelo",  "Volver"};

            ConsoleUtils.listarOpc(flightsOpc);
            int op = Integer.parseInt(sc.nextLine());

            // MENU DE ADMINISTRACION DE VUELOS. COMENZAR CON BUSCAR VUELOS

            switch (op) {
                case 1 -> {
                    // BUSCAR VUELO POR PARAMETROS
                    tripConsoleAdapter.getTripsByParameters();
                }
                case 2 -> {
                    // Buscar Viaje por ID
                    ConsoleUtils.limpiarConsola();
                    System.out.println("******************** CONSULTAR VUELO ********************");
                    tripConsoleAdapter.searchTrip();
                }
                case 3 -> {
                    // SELECCIONAR VUELO
                    tripBookingConsoleAdapter.selectTripBooking();
                }

                case 4 -> {
                    isActiveFlights = false;
                }
            
                default -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] OPCION INVALIDA");
                    sc.nextLine();
                }

            }
        }

    }
    
    public static void customerMenu() {
        Scanner sc = new Scanner(System.in);
        boolean isActiveCustomer = true;

        while (isActiveCustomer) {
            ConsoleUtils.limpiarConsola();
            menu();
            try {
                int opcMenu = Integer.parseInt(sc.nextLine().trim());

                switch (opcMenu) {
                    case 1 -> {
                        flightsMenu();
                    }

                    case 2 -> {
                        // "Cancelar Reserva", "Modificar Reserva",
                        
                    }
                    
                    case 3 -> {
                    
                    }

                    case 4 -> {
                        isActiveCustomer = false;
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
