package com.vuelos_globales.entities.TripBookingDetails.adapters.in;


import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.vuelos_globales.ConsoleUtils;
import com.vuelos_globales.entities.TripBookingDetails.application.TripBookingDetailsService;
import com.vuelos_globales.entities.TripBookingDetails.domain.TripBookingDetails;


public class TripBookingDetailsConsoleAdapter  {
    Scanner sc = new Scanner(System.in);

    private final TripBookingDetailsService tripBookingDetailsService;

    public TripBookingDetailsConsoleAdapter(TripBookingDetailsService tripBookingDetailsService) {
        this.tripBookingDetailsService = tripBookingDetailsService;
    }



    public void createTripBookingDetails(){
        String option = "S";

        while(option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR DETALLES DE RESERVA ***************");
            System.out.println("[*]  INGRESE EL ID DE DETALLE DE RESERVA DE VIAJE");
            String newId =  sc.nextLine();
            Optional<TripBookingDetails> tripBookingDetails = tripBookingDetailsService.getTripBookingDetailsById(newId);
            tripBookingDetails.ifPresentOrElse(
                tb -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", tb.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR DETALLES DE RESERVA DE VIAJE ***************");

                    System.out.println("[*] INGRESE EL ID DE LA RESERVA VIAJE : ");
                    String idTripBking = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL ID DE CLIENTE: ");
                    String idCustomer = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL ID DE TARIFA DE VUELO : ");
                    String idFlightFare = sc.nextLine();
            
                    TripBookingDetails newTripBookingDetails = new TripBookingDetails(newId, idTripBking, idCustomer, idFlightFare);
                    tripBookingDetailsService.createTripBookingDetails(newTripBookingDetails);
                }
            );
            System.out.println("[*]  DESEA CREAR OTRO DETALLE DE RESERVA? [S] SI | [CUALQUIER TECLA] NO");
            option = sc.nextLine();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        }
    }


    public void searchTripBookingDetails(){
        List<TripBookingDetails> tripBookingDetails = tripBookingDetailsService.getAllTripBookingDetails();

        if (tripBookingDetails.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN DETALLE DE RESERVA DE VIAJE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR DETALLE DE RESERVA DE VIAJE ***************");
            System.out.println("[?]  INGRESE EL ID DE DETALLES DE LA RESERVA DE VIAJE: ");
            String findId = sc.nextLine();

            Optional<TripBookingDetails> tripBookingDetail = tripBookingDetailsService.getTripBookingDetailsById(findId);
            tripBookingDetail.ifPresentOrElse(
                tb -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** DETALLES DE RESERVA ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] ID DE RESERVA DE VIAJE : {1}\n[*] ID DE CLIENTE: {2}\n[*] ID DE TARIFA DE VUELO: {3}", tb.getId(), tb.getIdTripBooking() + tb.getIdCustomer(), tb.getIdFlightFares()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  DETALLES DE RESERVA DE VIAJE NO ENCONTRADOS");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();

        }
        
    }


    public void updateTripBookingDetails() {
        List<TripBookingDetails> tripBookingDetails = tripBookingDetailsService.getAllTripBookingDetails();
    
        if (tripBookingDetails.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN DETALLE DE RESERVA VIAJE REGISTRADO");
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL DETALLE DE RESERVA VIAJE A BUSCAR: ");
            String findId = sc.nextLine();
    
            Optional<TripBookingDetails> tripBookingDetail = tripBookingDetailsService.getTripBookingDetailsById(findId);
            tripBookingDetail.ifPresentOrElse(
                tb -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("***** ACTUALIZAR DETALLES DE RESERVA VIAJE *****");
                    System.out.println(MessageFormat.format(
                        "[] ID: {0}\n[] ID DE RESERVA DE VIAJE: {1}\n[] ID DE CLIENTE: {2}\n[] ID DE TARIFAS DE VUELO: {3}",
                        tb.getId(), tb.getIdTripBooking(), tb.getIdCustomer(), tb.getIdFlightFares()
                    ));
    
                    System.out.println("[?] INGRESE EL NUEVO ID DE LA RESERVA VIAJE: ");
                    String updateIdTripBog = sc.nextLine();
    
                    System.out.println("[?] INGRESE EL NUEVO ID DE CLIENTE: ");
                    String updateIdCusto = sc.nextLine();
    
                    System.out.println("[?] INGRESE EL NUEVO ID DE TARIFA DE VUELO: ");
                    String updateIdFlightF = sc.nextLine();
    

                    TripBookingDetails updatedTripBookingDetails = new TripBookingDetails(findId, updateIdTripBog, updateIdCusto, updateIdFlightF);
                    tripBookingDetailsService.updateTripBookingDetails(updatedTripBookingDetails);
                    sc.nextLine();

                    ConsoleUtils.limpiarConsola();
                    System.out.println("[*] DETALLE DE RESERVA DE VIAJE ACTUALIZADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] DETALLE DE RESERVA DE VIAJE NO ENCONTRADO");
                    sc.nextLine();
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
    
    
    public void deleteTripBookingDetails(){
        List<TripBookingDetails> tripBookingDetails = tripBookingDetailsService.getAllTripBookingDetails();

        if (tripBookingDetails.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY DETALLES DE RESERVA DE VIAJE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL DETALLE DE RESERVA DE VIAJE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<TripBookingDetails> tripBookingDetail = tripBookingDetailsService.getTripBookingDetailsById(findId);
            tripBookingDetail.ifPresentOrElse(
                tb -> {
                    tripBookingDetailsService.deleteTripBookingDetails(findId);
                    System.out.println("[!] DETALLE DE RESERVA DE VIAJE ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!] DETALLE DE RESERVA DE VIAJE NO ENCONTRADO");
                });
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }


    public void getAllTripBookingDetails(){
        List<TripBookingDetails> TripBookingDetails = tripBookingDetailsService.getAllTripBookingDetails();
        if(TripBookingDetails.isEmpty()){
            ConsoleUtils.limpiarConsola();
            System.out.println("[!]  NO HAY DETALLES DE RESERVA DE VIAJE REGISTRADOS");
        }   else {
            System.out.println("[*]  DETALLES DE RESERVA DE VIAJE REGISTRADOS");
            tripBookingDetailsService.getAllTripBookingDetails().forEach(tb -> {
                System.out.println("[*]  ID: "+ tb.getId() + "ID DE RESERVA DE VIAJE: " + tb.getIdTripBooking()+ "ID DE CLIENTE : " + tb.getIdCustomer()+ "ID DE TARIFAS DE VUELO: " + tb.getIdFlightFares());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
       
        
    }
}