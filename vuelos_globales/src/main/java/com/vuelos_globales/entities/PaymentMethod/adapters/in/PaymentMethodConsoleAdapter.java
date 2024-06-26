package com.vuelos_globales.entities.PaymentMethod.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.PaymentMethod.domain.PaymentMethod;
import com.vuelos_globales.entities.PaymentMethod.application.PaymentMethodService;
import com.vuelos_globales.modules.ConsoleUtils;

public class PaymentMethodConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodConsoleAdapter(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    public void createPayMethod() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR METODO DE PAGO ***************");
            System.out.println("[*] INGRESE EL ID DEL METODO DE PAGO A CREAR: ");
            int id = sc.nextInt();
            Optional<PaymentMethod> paymentMethod = paymentMethodService.getPayMethodById(id);
            paymentMethod.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR METODO DE PAGO ***************");

                    System.out.println("[*] INGRESE EL NOMBRE DEL METODO DE PAGO: ");
                    String paymentMethodName = sc.nextLine();
    
                    PaymentMethod newPaymentMethod = new PaymentMethod(id, paymentMethodName);
                    paymentMethodService.createPayMethod(newPaymentMethod);
                });

            System.out.println("[?] DESEA AÑADIR OTRO METODO DE PAGO? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchPaymentMethod() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPayMethods();
        
        if (paymentMethods.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN METODO DE PAGO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR METODO DE PAGO ***************");
            System.out.println("[?] INGRESE EL ID DEL METODO DE PAGO A BUSCAR: ");
            int id = sc.nextInt();

            Optional<PaymentMethod> paymentMethod = paymentMethodService.getPayMethodById(id);
            paymentMethod.ifPresentOrElse(
                p -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** METODO DE PAGO ***************");
                    System.out.println(MessageFormat.format("[*] ID : {0}\n[*] METODO PAGO : {1}", p.getId(), p.getPaymentMethod()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!] METODO DE PAGO NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void getAllPayMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPayMethods();
        
        if (paymentMethods.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN METODO DE PAGO REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            paymentMethodService.getAllPayMethods().forEach(p -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n[*] METODO PAGO : {1}", p.getId(), p.getPaymentMethod()));
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}
