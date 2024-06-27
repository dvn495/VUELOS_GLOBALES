package com.vuelos_globales.modules.menus;

import java.util.Scanner;

import com.vuelos_globales.entities.BookingStatus.adapters.out.BookingStatusMySQLRepository;
import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.entities.Customer.adapters.in.CustomerConsoleAdapter;
import com.vuelos_globales.entities.Customer.adapters.out.CustomerMySQLRepository;
import com.vuelos_globales.entities.Customer.application.CustomerService;
import com.vuelos_globales.entities.DocumentType.adapters.out.DocTypeMySQLRepository;
import com.vuelos_globales.entities.Trip.adapters.in.TripConsoleAdapter;
import com.vuelos_globales.entities.Trip.adapters.out.TripMySQLRepository;
import com.vuelos_globales.entities.Trip.application.TripService;
import com.vuelos_globales.entities.TripBooking.adapters.in.TripBookingConsoleAdapter;
import com.vuelos_globales.entities.TripBooking.adapters.out.TripBookingMySQLRepository;
import com.vuelos_globales.entities.TripBooking.application.TripBookingService;
import com.vuelos_globales.modules.ConsoleUtils;

public class SalesRepresentativeMenu {

    public static void menu() {
        ConsoleUtils.limpiarConsola();
        System.out.println("------MENU REPRESENTANTE DE VENTAS ------");

        String[] salesRepOpc = {"CREAR RESERVA DE VIAJE","CREAR CLIENTE","CONSULTAR INFORMACION DE CLIENTE","ACTUALIZAR INFORMACION DE CLIENTE","VOLVER"};

        int i = 0;

        for (String opc : salesRepOpc) {
            i++;
            System.out.println("       " + i + ". " + opc);
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


        boolean isActiveSalesRep = true;

        while (isActiveSalesRep) {
            ConsoleUtils.limpiarConsola();
            menu();
            try {
                System.out.print("Seleccione una opción: ");
                int opcMenu = Integer.parseInt(sc.nextLine().trim());

                switch (opcMenu) {
                    case 1 -> {
                        TripBookingService tripBookingService = new TripBookingService(tripBookingMySQLRepository, tripMySQLRepository, bookingStatusMySQLRepository);
                        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService);
                        tripBookingConsoleAdapter.createTripBooking();
                    }

                    case 2 -> {
                        CustomerService customerService = new CustomerService(customerMySQLRepository, docTypeMySQLRepository);
                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                        customerConsoleAdapter.createCustomer();
                    }

                    case 3 -> {
                        CustomerService customerService = new CustomerService(customerMySQLRepository, docTypeMySQLRepository);
                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                        customerConsoleAdapter.searchCustomer();
                    }
                    case 4 -> {
                        CustomerService customerService = new CustomerService(customerMySQLRepository, docTypeMySQLRepository);
                        CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                        customerConsoleAdapter.updateCustomer();
                    }
                    case 5 -> {
                        isActiveSalesRep = false;
                    }
                    case 6 -> {
                        isActiveSalesRep = false;
                    }
                    case 7 -> {
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




