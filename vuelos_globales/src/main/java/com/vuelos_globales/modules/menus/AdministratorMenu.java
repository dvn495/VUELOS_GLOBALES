package com.vuelos_globales.modules.menus;

import java.util.Scanner;

import com.vuelos_globales.entities.Planes.adapters.in.PlanesConsoleController;
import com.vuelos_globales.entities.Planes.adapters.out.PlanesMySQLRepository;
import com.vuelos_globales.entities.Planes.application.PlanesService;
import com.vuelos_globales.modules.ConsoleUtils;


public class AdministratorMenu {
    public static void menu(){
        ConsoleUtils.limpiarConsola();
        System.out.println("------MENU ADMINISTRADOR------");

        String[] administratorOpc = {"REGISTRAR AVION", "VOLVER"};

        int i = 0;

        for (String opc: administratorOpc) {
            i++;
            System.out.println("       "+ i +". "+ opc);
        }
    }
    public static void administratorMenu(){
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/airport_database";
        String username = "campus2023";
        String password = "campus2023";

        PlanesMySQLRepository planesMySQLRepository = new PlanesMySQLRepository(url, username, password);

        boolean isActiveAdministrator = true;

        while (isActiveAdministrator) {
            ConsoleUtils.limpiarConsola();
            menu();
            try {
                int opcMenu = Integer.parseInt(sc.nextLine().trim());

                switch (opcMenu) {
                    case 1 -> {
                        PlanesService planesService = new PlanesService(planesMySQLRepository);
                        PlanesConsoleController planesConsoleController = new PlanesConsoleController(planesService);
                        planesConsoleController.createPlanes();
                    }

                    case 2 -> {
                        
                    }
                    
                    case 3 -> {
                        
                    }

                    case 4 -> {
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
