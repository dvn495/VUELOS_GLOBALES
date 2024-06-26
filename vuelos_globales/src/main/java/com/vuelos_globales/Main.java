package com.vuelos_globales;

import com.vuelos_globales.modules.ConsoleUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        System.out.println("[-] Que tipo de usuario eres?");
        String[] opcLogin = {"Administrador","Representante De Ventas","Tecnico De Mantenimiento","Cliente"};
    
        int i = 0;
        for (String opc : opcLogin) {
            i++;
            System.out.println(MessageFormat.format("       {0}. {1}", i, opc));
        }
    }


    
    public static void main(String[] args) {
        menuPrincipal();
    }
}
