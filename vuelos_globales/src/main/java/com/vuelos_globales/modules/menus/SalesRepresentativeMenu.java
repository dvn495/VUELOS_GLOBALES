package com.vuelos_globales.modules.menus;

import java.util.Scanner;

import com.vuelos_globales.entities.Customer.adapters.in.CustomerConsoleAdapter;
import com.vuelos_globales.entities.Customer.adapters.out.CustomerMySQLRepository;
import com.vuelos_globales.entities.Customer.application.CustomerService;
import com.vuelos_globales.entities.DocumentType.adapters.out.DocTypeMySQLRepository;
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


        boolean isActiveSalesRep = true;

        while (isActiveSalesRep) {
            ConsoleUtils.limpiarConsola();
            menu();
            try {
                System.out.print("Seleccione una opción: ");
                int opcMenu = Integer.parseInt(sc.nextLine().trim());

                switch (opcMenu) {
                    case 1 -> {

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




