package com.vuelos_globales.entities.Customer.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.List;

import com.vuelos_globales.entities.Customer.application.CustomerService;
import com.vuelos_globales.entities.Customer.domain.Customer;
import com.vuelos_globales.entities.DocumentType.domain.DocumentType;
import com.vuelos_globales.modules.ConsoleUtils;

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
            if (customer.isPresent()) {
                System.out.println(MessageFormat.format("[!] EL ID ({0}) YA ESTA OCUPADO.", id));
                ConsoleUtils.esperarEntrada();
                continue; 
            }

            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR CLIENTE ***************");
            System.out.println("[*] INGRESE EL NOMBRE DEL CLIENTE: ");
            String customerName = sc.nextLine();

            System.out.println("[*] INGRESE EL APELLIDO DEL CLIENTE: ");
            String customerLastName = sc.nextLine();

            int customerAge = 0;
            boolean validAge = false;
            while (!validAge) {
                try {
                    System.out.println("[*] INGRESE LA EDAD DEL CLIENTE: ");
                    customerAge = Integer.parseInt(sc.nextLine().trim());
                    validAge = true;
                } catch (NumberFormatException e) {
                    System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA EDAD DEL CLIENTE.");
                }
            }

            int documentNumber = 0;
            boolean validDocumentNumber = false;
            while (!validDocumentNumber) {
                try {
                    System.out.println("[*] INGRESE EL NUMERO DE DOCUMENTO DEL CLIENTE: ");
                    documentNumber = Integer.parseInt(sc.nextLine().trim());
                    validDocumentNumber = true;
                } catch (NumberFormatException e) {
                    System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA NUMERO DE DOCUMENTO.");
                }
            }

            // DOCUMENT TYPE
            int idDocumentType = 0;
            boolean validDocumentType = false;
            while (!validDocumentType) {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** ASIGNAR TIPO DE DOCUMENTO ***************");
                List<DocumentType> documentTypes = customerService.getAllDocumentTypes();
                for (DocumentType documentType : documentTypes) {
                    System.out.println(MessageFormat.format("[*] ID: {0} - TIPO: {1}", documentType.getId(), documentType.getDocumentType()));
                }

                try {
                    System.out.println("[*] INGRESE EL ID DEL TIPO DE DOCUMENTO A ASIGNAR: ");
                    idDocumentType = Integer.parseInt(sc.nextLine().trim());

                    Optional<DocumentType> documentType = customerService.getDocumentTypeById(idDocumentType);
                    if (documentType.isPresent()) {
                        validDocumentType = true;
                    } else {
                        System.out.println(MessageFormat.format("[!] EL ID ({0}) NO EXISTE.", idDocumentType));
                        ConsoleUtils.esperarEntrada();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA ID DE DOCUMENTO.");
                    ConsoleUtils.esperarEntrada();
                }
            }

            Customer newCustomer = new Customer(id, customerName, customerLastName, customerAge, documentNumber, idDocumentType);
            customerService.createCustomer(newCustomer);

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
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] APELLIDO : {2}\n[*] EDAD: {3}\n[*] NUMERO DE DOCUMENTO: {4}\n[*] DOCUMENTO ID: {5}", c.getId(), c.getName(), c.getLastName(), c.getAge(), c.getDocumentNumber(), c.getIdDocumentType()));
                    ConsoleUtils.esperarEntrada();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] CLIENTE NO ENCONTRADO");
                    ConsoleUtils.esperarEntrada();
                });
            ConsoleUtils.limpiarConsola();
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
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
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] APELLIDO : {2}\n[*] EDAD: {3}\n[*] DOCUMENTO ID: {4}\n[*] NUMERO DE DOCUMENTO: {5}", c.getId(), c.getName(), c.getLastName(), c.getAge(), c.getIdDocumentType(), c.getDocumentNumber()));

                    System.out.println("[?] INGRESE EL NUEVO NOMBRE DEL CLIENTE: ");
                    String updateName = sc.nextLine();

                    System.out.println("[?] INGRESE EL NUEVO APELLIDO DEL CLIENTE: ");
                    String updateLastName = sc.nextLine();

                    int updateAge = 0;
                    boolean validAge = false;
                    while (!validAge) {
                        try {
                            System.out.println("[?] INGRESE LA NUEVA EDAD DEL CLIENTE: ");
                            updateAge = Integer.parseInt(sc.nextLine().trim());
                            validAge = true;
                        } catch (NumberFormatException e) {
                            System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA EDAD DEL CLIENTE.");
                        }
                    }

                    int updateIdDocumentType = 0;
                    boolean validDocumentType = false;
                    while (!validDocumentType) {
                        ConsoleUtils.limpiarConsola();
                        System.out.println("*************** ASIGNAR NUEVO TIPO DE DOCUMENTO ***************");
                        List<DocumentType> documentTypes = customerService.getAllDocumentTypes();
                        for (DocumentType documentType : documentTypes) {
                            System.out.println(MessageFormat.format("[*] ID: {0} - TIPO: {1}", documentType.getId(), documentType.getDocumentType()));
                        }

                        try {
                            System.out.println("[?] INGRESE EL ID DEL NUEVO TIPO DE DOCUMENTO: ");
                            updateIdDocumentType = Integer.parseInt(sc.nextLine().trim());

                            Optional<DocumentType> documentType = customerService.getDocumentTypeById(updateIdDocumentType);
                            if (documentType.isPresent()) {
                                validDocumentType = true;
                            } else {
                                System.out.println(MessageFormat.format("[!] EL ID ({0}) NO EXISTE.", updateIdDocumentType));
                                ConsoleUtils.esperarEntrada();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("[!] INGRESASTE UNA OPCION INVALIDA PARA ID DE DOCUMENTO.");
                            ConsoleUtils.esperarEntrada();
                        }
                    }

                    Customer updatedCustomer = new Customer(findId, updateName, updateLastName, updateAge, updateAge, updateIdDocumentType);
                    customerService.updateCustomer(updatedCustomer);
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
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
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
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A ELIMINAR: ");
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
            ConsoleUtils.limpiarConsola();
            customerService.getAllCustomers().forEach(c -> {
                System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] APELLIDO : {2}\n[*] EDAD: {3}\n[*] NUMERO DE DOCUMENTO: {4}\n[*] DOCUMENTO ID: {5}", c.getId(), c.getName(), c.getLastName(), c.getAge(), c.getDocumentNumber(), c.getIdDocumentType()));
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}

