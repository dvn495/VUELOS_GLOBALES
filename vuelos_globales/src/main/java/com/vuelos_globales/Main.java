package com.vuelos_globales;

import java.text.MessageFormat;
import java.util.Scanner;

import com.vuelos_globales.modules.ConsoleUtils;
import com.vuelos_globales.modules.menus.AdministratorMenu;
import com.vuelos_globales.modules.menus.CustomerMenu;
import com.vuelos_globales.modules.menus.SalesRepresentativeMenu;

public class Main {
    public static void menuPrincipal() {
        ConsoleUtils.limpiarConsola();

        String banner = "░█████╗░██╗██████╗░██████╗░░█████╗░██████╗░████████╗\n" +
                        "██╔══██╗██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝\n" +
                        "███████║██║██████╔╝██████╔╝██║░░██║██████╔╝░░░██║░░░\n" +
                        "██╔══██║██║██╔══██╗██╔═══╝░██║░░██║██╔══██╗░░░██║░░░\n" + 
                        "██║░░██║██║██║░░██║██║░░░░░╚█████╔╝██║░░██║░░░██║░░░\n" +
                        "╚═╝░░╚═╝╚═╝╚═╝░░╚═╝╚═╝░░░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░\n";

        System.out.println(banner);

        System.out.println("Bienvenido al sistema administrativo de vuelos globales");
        System.out.println("[-] Que tipo de usuario eres?\n");
        String[] opcLogin = {"Administrador","Representante De Ventas","Tecnico De Mantenimiento","Cliente","Salir"};
    
        int i = 0;
        for (String opc : opcLogin) {
            i++;
            System.out.println(MessageFormat.format("       {0}. {1}", i, opc));
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isActiveApp = true;

        while (isActiveApp) {
            try {
                menuPrincipal();
                int opMain = Integer.parseInt(sc.nextLine().trim());
    
                switch (opMain) {
                    case 1 -> {
                        AdministratorMenu.menu();
                        break;
                    }
    
                    case 2 -> {
                        SalesRepresentativeMenu.menu();
                        SalesRepresentativeMenu.salesRepresentativeMenu();
                    }
    
                    case 3 -> {
    
                    }
    
                    case 4 -> {
                        CustomerMenu.customerMenu();
                    }

                    case 5 -> {
                        System.out.println("[!] Saliendo...");
                        isActiveApp = false;
                    }
    
                    default -> {
                        System.out.println("[!] Ingresaste una opción inválida.");
                        sc.nextLine();
                        isActiveApp = false;
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
