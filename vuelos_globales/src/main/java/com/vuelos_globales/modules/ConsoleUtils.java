package com.vuelos_globales.modules;

public class ConsoleUtils {
    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
