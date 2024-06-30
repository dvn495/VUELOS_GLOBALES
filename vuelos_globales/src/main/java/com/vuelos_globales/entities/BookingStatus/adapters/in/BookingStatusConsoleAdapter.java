package com.vuelos_globales.entities.BookingStatus.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.BookingStatus.domain.BookingStatus;
import com.vuelos_globales.modules.ConsoleUtils;
import com.vuelos_globales.entities.BookingStatus.application.BookingStatusService;

public class BookingStatusConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final BookingStatusService bookingStatusService;

    public BookingStatusConsoleAdapter(BookingStatusService bookingStatusService) {
        this.bookingStatusService = bookingStatusService;
    }

    public void createBookingStatus() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR ESTADO DE RESERVA ***************");
            System.out.println("[*] INGRESE EL ID DEL ESTADO DE RESERVA A CREAR: ");
            int id = sc.nextInt();
            Optional<BookingStatus> bookingStatus = bookingStatusService.getBookingStatusById(id);
            bookingStatus.ifPresentOrElse(
                a -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", a.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR ESTADO DE RESERVA ***************");

                    System.out.println("[*] INGRESE EL NOMBRE DEL ESTADO DE RESERVA: ");
                    String bookingStatusName = sc.nextLine();
    
                    BookingStatus newBookingStatus = new BookingStatus(id, bookingStatusName);
                    bookingStatusService.createBookingStatus(newBookingStatus);
                });

            System.out.println("[?] DESEA AÑADIR OTRO ESTADO DE RESERVA? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchBookingStatus() {
        List<BookingStatus> bookingStatuses = bookingStatusService.getAllBookingStatuses();
        
        if (bookingStatuses.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN ESTADO DE RESERVA REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR ESTADO DE RESERVA ***************");
            System.out.println("[?] INGRESE EL ID DEL ESTADO DE RESERVA A BUSCAR: ");
            int findId = sc.nextInt();

            Optional<BookingStatus> bookingStatus = bookingStatusService.getBookingStatusById(findId);
            bookingStatus.ifPresentOrElse(
                e -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** ESTADO DE RESERVA ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] ESTADO : {1}", e.getId(), e.getBookingStatus()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  ESTADO DE RESERVA NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void getAllBookingStatuss() {
        List<BookingStatus> bookingStatuses = bookingStatusService.getAllBookingStatuses();
        
        if (bookingStatuses.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN ESTADO DE RESERVA REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            bookingStatusService.getAllBookingStatuses().forEach(e -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n[*] ESTADO : {1}", e.getId(), e.getBookingStatus())); 
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}

