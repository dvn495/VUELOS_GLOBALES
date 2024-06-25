package com.vuelos_globales.Customer.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.List;

import com.vuelos_globales.ConsoleUtils;
import com.vuelos_globales.Customer.domain.Customer;
import com.vuelos_globales.Customer.application.CustomerService;

// INYECCION


public class CustomerConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final CustomerService customerService;

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void createCustomer() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR CLIENTE ***************");
            System.out.println("[*] INGRESE EL ID DEL CLIENTE A CREAR: ");
            String id = sc.nextLine();
            Optional<Customer> customer = customerService.getCustomerById(id);
            customer.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR CLIENTE ***************");

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

    public void searchCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR CLIENTE ***************");
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Customer> customer = customerService.getCustomerById(findId);
            customer.ifPresentOrElse(
                c -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** CLIENTE ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] EDAD: {2}\n[*] DOCUMENTO: {3}", c.getId(), c.getName() + c.getLastName(), c.getIdDocumentType()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  CLIENTE NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void updateCustomer() {
        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Customer> customer = customerService.getCustomerById(findId);
            customer.ifPresentOrElse(
            c -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** ACTUALIZAR CLIENTE ***************");
                System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] EDAD: {2}\n[*] DOCUMENTO: {3}", c.getId(), c.getName() + c.getLastName(), c.getIdDocumentType()));

                System.out.println("[?] INGRESE EL NOMBRE DEL CLIENTE : ");
                String updateName = sc.nextLine();

                System.out.println("[?] INGRESE EL APELLIDO DEL CLIENTE : ");
                String updateLastName = sc.nextLine();

                System.out.println("[?] INGRESE LA EDAD DEL CLIENTE : ");
                int updateAge = sc.nextInt();

                System.out.println("[?] INGRESE TIPO DE DOCUMENTO DEL CLIENTE : ");
                int updateCostumerClient = sc.nextInt();
            
                Customer updatedCustomer = new Customer(c.getId(), updateName, updateLastName, updateAge, updateCostumerClient);
                customerService.updateCustomer(updatedCustomer);
                sc.nextLine();

                ConsoleUtils.limpiarConsola();
                System.out.println("[*] CLIENTE ACTUALIZADO CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] CLIENTE NO ENCONTRADO");
                sc.nextLine();
            }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void deleteCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Customer> customer = customerService.getCustomerById(findId);
            customer.ifPresentOrElse(
                c -> {
                    customerService.deleteCustomer(findId);
                    System.out.println("[!] CLIENTE ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  CLIENTE NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        
        if (customers.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            customerService.getAllCustomers().forEach(c -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] EDAD: {2}\n[*] DOCUMENTO: {3}", c.getId(), c.getName() + c.getLastName(), c.getIdDocumentType())); 
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}
