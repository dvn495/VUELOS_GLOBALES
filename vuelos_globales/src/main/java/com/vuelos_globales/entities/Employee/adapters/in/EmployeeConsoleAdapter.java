package com.vuelos_globales.entities.Employee.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Employee.domain.Employee;
import com.vuelos_globales.entities.Employee.application.EmployeeService;
import com.vuelos_globales.modules.ConsoleUtils;

public class EmployeeConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final EmployeeService employeeService;

    public EmployeeConsoleAdapter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void createEmployee() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR EMPLEADO ***************");
            System.out.println("[*] INGRESE EL ID DEL EMPLEADO A CREAR: ");
            String id = sc.nextLine();
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            employee.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR EMPLEADO ***************");

                    System.out.println("[*] INGRESE EL NOMBRE DEL EMPLEADO: ");
                    String employeeName = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL APELLIDO DEL EMPLEADO: ");
                    String employeeLastName = sc.nextLine();
    
                    System.out.println("[*] INGRESE LA FECHA DE INGRESO DEL EMPLEADO: ");
                    String employeeIngress = sc.nextLine();
    
                    System.out.println("[*] INGRESE EL ROL DEL EMPLEADO: ");
                    String employeeRole = sc.nextLine();

                    System.out.println("[*] INGRESE LA AEREOLINEA DEL EMPLEADO: ");
                    String employeeAirline = sc.nextLine();

                    System.out.println("[*] INGRESE EL AEREOPUERTO DEL EMPLEADO: ");
                    String employeeAirport = sc.nextLine();
    
                    Employee newEmployee = new Employee(id, employeeName, employeeLastName, employeeIngress, employeeRole, employeeAirline, employeeAirport);
                    employeeService.createEmployee(newEmployee);
                });

            System.out.println("[?] DESEA AÑADIR OTRO EMPLEADO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchEmployee() {
        List<Employee> Employees = employeeService.getAllEmployees();
        
        if (Employees.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN EMPLEADO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR EMPLEADO ***************");
            System.out.println("[?] INGRESE EL ID DEL EMPLEADO A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Employee> Employee = employeeService.getEmployeeById(findId);
            Employee.ifPresentOrElse(
                e -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** EMPLEADO ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] APELLIDO : {2}\n[*] FECHA DE INGRESO: {3}\n[*] ROL : {4}\n[*] AEREOLINEA : {5}\n[*] AEREOPUERTO : {6}", e.getId(), e.getName() + e.getLastName(), e.getIngressDate(), e.getIdRole(), e.getIdAirline(), e.getIdAirport()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] EMPLEADO NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void updateEmployee() {
        List<Employee> Employees = employeeService.getAllEmployees();

        if (Employees.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN EMPLEADO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL EMPLEADO A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Employee> Employee = employeeService.getEmployeeById(findId);
            Employee.ifPresentOrElse(
            c -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** REGISTRAR EMPLEADO ***************");

                System.out.println("[*] INGRESE EL NOMBRE DEL EMPLEADO: ");
                String employeeName = sc.nextLine();

                System.out.println("[*] INGRESE EL APELLIDO DEL EMPLEADO: ");
                String employeeLastName = sc.nextLine();

                System.out.println("[*] INGRESE LA FECHA DE INGRESO DEL EMPLEADO: ");
                String employeeIngress = sc.nextLine();

                System.out.println("[*] INGRESE EL ROL DEL EMPLEADO: ");
                String employeeRole = sc.nextLine();

                System.out.println("[*] INGRESE LA AEREOLINEA DEL EMPLEADO: ");
                String employeeAirline = sc.nextLine();

                System.out.println("[*] INGRESE EL AEREOPUERTO DEL EMPLEADO: ");
                String employeeAirport = sc.nextLine();

                Employee newEmployee = new Employee(findId, employeeName, employeeLastName, employeeIngress, employeeRole, employeeAirline, employeeAirport);
                employeeService.updateEmployee(newEmployee);

                ConsoleUtils.limpiarConsola();
                System.out.println("[*] EMPLEADO ACTUALIZADO CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] EMPLEADO NO ENCONTRADO");
                sc.nextLine();
            }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void deleteEmployee() {
        List<Employee> employees = employeeService.getAllEmployees();
        
        if (employees.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN EMPLEADO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL EMPLEADO A ELIMINAR: ");
            String findId = sc.nextLine();

            Optional<Employee> employee = employeeService.getEmployeeById(findId);
            employee.ifPresentOrElse(
                c -> {
                    employeeService.deleteEmployee(findId);
                    System.out.println("[!] EMPLEADO ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  EMPLEADO NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        
        if (employees.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN EMPLEADO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            employeeService.getAllEmployees().forEach(e -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n[*] NOMBRE : {1}\n[*] APELLIDO : {2}\n[*] FECHA DE INGRESO: {3}\n[*] ROL : {4}\n[*] AEREOLINEA : {5}\n[*] AEREOPUERTO : {6}", e.getId(), e.getName() + e.getLastName(), e.getIngressDate(), e.getIdRole(), e.getIdAirline(), e.getIdAirport()));
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
    
}
