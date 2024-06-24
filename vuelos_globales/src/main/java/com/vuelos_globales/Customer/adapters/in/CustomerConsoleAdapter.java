package com.vuelos_globales.Customer.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.List;

import com.vuelos_globales.Customer.domain.Customer;
import com.vuelos_globales.Customer.application.CustomerService;

// INYECCION


public class CustomerConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final CustomerService customerService;

    public CustomerConsoleAdapter(CustomerService customerService);

    public void createCustomer() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            System.out.println("[*] INGRESE EL ID DEL CLIENTE A CREAR: ");
            String id = sc.nextLine();
            Optional<Customer> customer = customerService.getCustomerById(id);
            customer.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    System.out.println("[*] INGRESE EL NOMBRE DEL CLIENTE: ");
                    String customerName = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL APELLIDO DEL CLIENTE: ");
                    String customerLastName = sc.nextLine();
    
                    System.out.println("[*] INGRESE LA EDAD DEL CLIENTE: ");
                    int customerAge = sc.nextInt();
    
                    System.out.println("[*] INGRESE EL TIPO DE DOCUMENTO DEL CLIENTE: ");
                    int customerDocType = sc.nextInt();
    
                    Customer newCustomer = new Customer(id, customerName, customerLastName, customerAge, customerDocType);
                    customerService.createCustomer(newCustomer);
                });

            System.out.println("[?] DESEA AÑADIR OTRO CLIENTE? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }
}
