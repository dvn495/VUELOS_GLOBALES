package com.vuelos_globales.entities.RevEmployee.adapters.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import com.vuelos_globales.modules.ConsoleUtils;
import com.vuelos_globales.entities.RevEmployee.application.RevEmployeesService;
import com.vuelos_globales.entities.RevEmployee.domain.RevEmployees;
import com.vuelos_globales.modules.ConsoleUtils;

public class RevEmployeesConsoleController {
    Scanner sc = new Scanner(System.in);

    private final RevEmployeesService RevEmployeesService;

    public RevEmployeesConsoleController(RevEmployeesService RevEmployeesService) {
        this.RevEmployeesService = RevEmployeesService;
    }

    public void createRevEmployee(){
        String option = "S";

        while(option.equalsIgnoreCase("S")){
            ConsoleUtils.limpiarConsola();
            System.out.println("[*]  INGRESE EL ID DE LA REVISION DEL EMPLEADO");
            String newId = sc.nextLine();

            Optional<RevEmployees> RevEmployee = RevEmployeesService.findById(newId);
            RevEmployee.ifPresentOrElse(
                g ->  {
                    System.out.println("[!]  REVISION YA EXISTENTE");
                    System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                    sc.nextLine();
                },
                ()-> {

                    System.out.println("\n[*]  INGRESE EL ID DEL EMPLEADO");
                    String newIdEmployee = sc.nextLine();

                    System.out.println("\n[*]  INGRESE EL ID DE LA REVISION");
                    String newIdRevision= sc.nextLine();
                      
                    RevEmployees revEmployees = new RevEmployees(newId, newIdEmployee, newIdRevision);
                    RevEmployeesService.createRevEmployee(revEmployees);
                }
            );
            System.out.println("[*]  DESEA REGISTRAR OTRA REVISION? [S] SI | [CUALQUIER TECLA] NO");
            option = sc.nextLine();   
        }  
    }

    public void searchRevEmployee(){
        ConsoleUtils.limpiarConsola();
        List<RevEmployees> revEmployeeList = RevEmployeesService.getAllRevEmployees();

        if(revEmployeeList.isEmpty()){
            System.out.println("[!]  NO HAY REVISIONES REGISTRADAS");
        }else {
            System.out.println("[*]  INGRESE EL ID DE LA REVISION\n\n");
            String findId = sc.nextLine();

            Optional<RevEmployees> RevEmployees = RevEmployeesService.findById(findId);
            RevEmployees.ifPresentOrElse(
                f -> System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  ID DEL EMPLEADO: " + f.getIdEmployee() + "\n  [*]  ID DE LA REVISION: " + f.getIdRevision()),
                () -> System.out.println("[!]  REVISION NO ENCONTRADA")
            );
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void updateRevEmployee(){
        ConsoleUtils.limpiarConsola();
        List<RevEmployees> revEmployeesList = RevEmployeesService.getAllRevEmployees();

        if(revEmployeesList.isEmpty()){

            System.out.println("[!]  NO HAY REVISIONES REGISTRADAS");

        }  else {

            System.out.println("[*]  INGRESE EL ID DE LA REVISION A EDITAR\n\n");
            String findId = sc.nextLine();

            Optional<RevEmployees> revEmployee = RevEmployeesService.findById(findId);
            revEmployee.ifPresentOrElse(
            f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  ID DEL EMPLEADO: " + f.getIdEmployee() + "\n  [*]  ID DE LA REVISION: " + f.getIdRevision());

                String updateId = f.getId();

                System.out.println("[*]  INGRESE EL NUEVO ID DEL EMPLEADO");
                String updateIdEmployee = sc.nextLine();

                System.out.println("[*]  INGRESE EL NUEVO ID DE LA REVISION");
                String updateIdRevision = sc.nextLine();

                RevEmployees updateRevEmployees = new RevEmployees (updateIdEmployee, updateIdRevision,updateId);
                RevEmployeesService.updateRevEmployee(updateRevEmployees);
            },
            () -> System.out.println("[!]  REVISION NO ENCONTRADA")
        );
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
        }
    }

    public void deleteRevEmployees(){
        ConsoleUtils.limpiarConsola();
        List<RevEmployees> RevEmployee = RevEmployeesService.getAllRevEmployees();
        if(RevEmployee.isEmpty()){
            System.out.println("[!]  NO HAY REVISIONES REGISTRADAS");
        }   else {
            System.out.println("[*]  INGRESE EL ID DE LA REVISION A ELIMINAR\n\n");
            String findId = sc.nextLine();

            Optional<RevEmployees> RevEmployees = RevEmployeesService.findById(findId);
            RevEmployees.ifPresentOrElse(
                f -> RevEmployeesService.deleteRevEmployee(findId),
                () -> System.out.println("[!]  REVISION NO ENCONTRADA")
            );
        } 
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }

    public void getAllRevEmployees(){
        ConsoleUtils.limpiarConsola();
        List<RevEmployees> RevEmployees = RevEmployeesService.getAllRevEmployees();
        if(RevEmployees.isEmpty()){
            System.out.println("[!]  NO HAY REVISIONES REGISTRADAS");
        } else {
            RevEmployeesService.getAllRevEmployees().forEach(f -> {
                System.out.println("  [*]  ID: "+ f.getId() + "\n  [*]  ID DEL EMPLEADO: " + f.getIdEmployee() + "\n  [*]  ID DE LA REVISION: " + f.getIdRevision());
            });
        }
        System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
        sc.nextLine();
    }
}
