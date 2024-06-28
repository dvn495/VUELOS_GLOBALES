package com.vuelos_globales.modules;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;

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

    public static void listarOpc(String[] opciones) {
        int i = 1;
        for (String op : opciones) {
            System.out.println(MessageFormat.format("{0} . {1}", i++, op));
        }
    }

}