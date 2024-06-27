package com.vuelos_globales.modules;

import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner sc = new Scanner(System.in);

    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void esperarEntrada() {
        System.out.println("PRESIONA ENTER PARA CONTINUAR");
        sc.nextLine();
    }
}