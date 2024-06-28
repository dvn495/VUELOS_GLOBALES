package com.vuelos_globales.modules.menus;

import java.text.MessageFormat;
import java.util.Scanner;

import com.vuelos_globales.modules.ConsoleUtils;


public class CustomerMenu {
    public static void menu() {
        ConsoleUtils.limpiarConsola();

        String[] customerOpc = {"Administrar Vuelos", "Administrar Reservas", "Pagos","Volver"};

        int i = 0;
        for (String opc : customerOpc) {
            i++;
            System.out.println(MessageFormat.format("       {0}. {1}", i, opc));
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
