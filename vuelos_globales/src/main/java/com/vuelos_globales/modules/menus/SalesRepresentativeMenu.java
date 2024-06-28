package com.vuelos_globales.modules.menus;

import java.util.Scanner;

import com.vuelos_globales.entities.BookingStatus.adapters.out.BookingStatusMySQLRepository;
import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.Customer.adapters.in.CustomerConsoleAdapter;
import com.vuelos_globales.entities.Customer.adapters.out.CustomerMySQLRepository;
import com.vuelos_globales.entities.Customer.application.CustomerService;
import com.vuelos_globales.entities.DocumentType.adapters.out.DocTypeMySQLRepository;
import com.vuelos_globales.entities.FlightFares.adapters.out.FlightFaresMySQLRepository;
import com.vuelos_globales.entities.Trip.adapters.out.TripMySQLRepository;
import com.vuelos_globales.entities.TripBooking.adapters.out.TripBookingMySQLRepository;
import com.vuelos_globales.entities.TripBookingDetails.adapters.in.TripBookingDetailsConsoleAdapter;
import com.vuelos_globales.entities.TripBookingDetails.adapters.out.TripBookingDetailsMySQLRepository;
import com.vuelos_globales.entities.TripBookingDetails.application.TripBookingDetailsService;
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
        String username = "root";
        String password = "AuzimoLOAD789";


        CustomerMySQLRepository customerMySQLRepository = new CustomerMySQLRepository(url, username, password);
        DocTypeMySQLRepository docTypeMySQLRepository = new DocTypeMySQLRepository(url, username, password);
        TripMySQLRepository tripMySQLRepository = new TripMySQLRepository(url, username, password);
        BookingStatusMySQLRepository bookingStatusMySQLRepository = new BookingStatusMySQLRepository(url, username, password);
        TripBookingMySQLRepository tripBookingMySQLRepository = new TripBookingMySQLRepository(url, username, password);
        TripBookingDetailsMySQLRepository tripBookingDetailsMySQLRepository = new TripBookingDetailsMySQLRepository(url, username, password);
        FlightFaresMySQLRepository flightFaresMySQLRepository = new FlightFaresMySQLRepository(url, username, password);

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
                        TripBookingDetailsService tripBookingDetailsService = new TripBookingDetailsService(tripBookingDetailsMySQLRepository, tripMySQLRepository, customerMySQLRepository, flightFaresMySQLRepository);
                        TripBookingDetailsConsoleAdapter tripBookingDetailsConsoleAdapter = new TripBookingDetailsConsoleAdapter(tripBookingDetailsService);
                        tripBookingDetailsConsoleAdapter.createTripBookingDetails();
                    }

                    // CONSULTAR INFORMACION DE CLIENTE
                    case 2 -> {
                        CustomerService customerService = new CustomerService(customerMySQLRepository, docTypeMySQLRepository);
                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                        customerConsoleAdapter.searchCustomer();
                    }

                    //CONSULTAR RESERVA DE VUELO
                    case 3 -> {
                        
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

                    }

                    //CONSULTAR INFORMACION DE VUELO
                    case 7 -> {
                        
                    }

                    //CONSULTAR ASIGNACION DE TRIPULACION
                    case 8 -> {
                        
                    }

                    //CONSULTAR ESCALAS DE UN TRAYECTO
                    case 9-> {
                        
                    }

                    //CONSULTAR TIPO DE DOCUMENTO
                    case 10 -> {
                        
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

        sc.close();
    }
}




// ESTO ES PARA CREAR UN VIAJE:

// TripService tripService = new TripService(tripMySQLRepository, bookingStatusMySQLRepository, tripBookingMySQLRepository);
// TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);
// tripConsoleAdapter.createTrip();




