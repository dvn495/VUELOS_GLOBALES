package com.vuelos_globales.modules.menus;

import java.text.MessageFormat;
import java.util.Scanner;

import com.vuelos_globales.modules.ConsoleUtils;


public class CustomerMenu {
    public static void menu() {
        ConsoleUtils.limpiarConsola();

        String[] customerOpc = {"Administrar Vuelos", "Administrar Reservas", "Pagos","Volver"};

        ConsoleUtils.listarOpc(customerOpc);
    }

    public static void flightsMenu() {
        Scanner sc = new Scanner(System.in);
        boolean isActiveFlights = true;

        while (isActiveFlights) {
            ConsoleUtils.limpiarConsola();
            System.out.println("******************** ADMINISTRAR VUELOS ********************");
            String[] flightsOpc = {"Filtrar Vuelo", "Consultar Vuelo", "Seleccionar Vuelos", "Cancelar Reserva", "Modificar Reserva", "Volver"};

            ConsoleUtils.listarOpc(flightsOpc);
            int op = Integer.parseInt(sc.nextLine());

            // MENU DE ADMINISTRACION DE VUELOS. COMENZAR CON BUSCAR VUELOS

            switch (op) {
                case 1 -> {
                    // BUSCAR VUELO findByParameters(tripDate, idCiudadA, idCiudadB)
                }
                case 2 -> {
                    // getTripBookingById(id)
                }
                case 3 -> {
                    // selectTripBooking()
                }
                case 4 -> {
                    // cancelTripBooking(id)
                }
                case 5 -> {
                    // updateTripBooking
                }
                case 6 -> {
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
