package com.vuelos_globales.modules.menus;

import java.util.Scanner;

import com.vuelos_globales.entities.TripBooking.domain.TripBooking;
import com.vuelos_globales.entities.TripBookingDetails.adapters.out.TripBookingDetailsMySQLRepository;
import com.vuelos_globales.entities.TripBooking.adapters.in.TripBookingConsoleAdapter;
import com.vuelos_globales.entities.TripBooking.application.TripBookingService;
import com.vuelos_globales.entities.TripBooking.adapters.out.TripBookingMySQLRepository;
import com.vuelos_globales.modules.ConsoleUtils;

public class SalesRepresentativeMenu {

    public static void menu() {
        ConsoleUtils.limpiarConsola();
        System.out.println("------MENU REPRESENTANTE DE VENTAS ------");

        String[] SalesRepOpc = {"CREAR RESERVA DE VUELO", "VOLVER"};

        int i = 0;

        for (String opc : SalesRepOpc) {
            i++;
            System.out.println("       " + i + ". " + opc);
        }
    }

    public static void salesRepresentativeMenu() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/airport_database";
        String username = "campus2023";
        String password = "campus2023";

        TripBookingMySQLRepository tripBookingMySQLRepository = new TripBookingMySQLRepository(url, username, password);
        // TripBookingDetailsMySQLRepository tripBookingDetailsMySQLRepository = new TripBookingDetailsMySQLRepository(url, username, password);


        boolean isActiveSalesRep = true;

        while (isActiveSalesRep) {
            ConsoleUtils.limpiarConsola();
            menu();
            try {
                int opcMenu = Integer.parseInt(sc.nextLine().trim());

                switch (opcMenu) {
                    case 1 -> {
                        TripBookingService tripBookingService = new TripBookingService(tripBookingMySQLRepository);
                        TripBookingConsoleAdapter tripBookingConsoleAdapter = new TripBookingConsoleAdapter(tripBookingService);
                        tripBookingConsoleAdapter.createTripBooking();

                    }

                    case 2 -> {
                        isActiveSalesRep = false;

                    }

                    case 5 -> {

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



