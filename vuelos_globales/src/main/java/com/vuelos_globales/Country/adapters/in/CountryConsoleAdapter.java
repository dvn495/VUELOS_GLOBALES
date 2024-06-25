package com.vuelos_globales.Country.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.ConsoleUtils;
import com.vuelos_globales.Country.application.CountryService;
import com.vuelos_globales.Country.domain.Country;

public class CountryConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final CountryService countryService;

    public CountryConsoleAdapter(CountryService countryService) {
        this.countryService = countryService;
    }

    public void createCountry() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR PAIS ***************");
            System.out.println("[*] INGRESE EL ID DEL PAIS A CREAR: ");
            String id = sc.nextLine();
            Optional<Country> country = countryService.getCountryById(id);
            country.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR PAIS ***************");
            
                    System.out.println("[*] INGRESE EL NOMBRE DEL PAIS: ");
                    String countryName = sc.nextLine();
            
                    Country newCountry = new Country(id, countryName);
                    countryService.createCountry(newCountry);
                });

            System.out.println("[?] DESEA AÑADIR OTRO PAIS? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchCountry() {
        List<Country> countries = countryService.getAllCountries();
        
        if (countries.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN PAIS REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR PAIS ***************");
            System.out.println("[?] INGRESE EL ID DEL PAIS A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Country> country = countryService.getCountryById(findId);
            country.ifPresentOrElse(
                c -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** PAIS ***************");
                    System.out.println(MessageFormat.format("*] ID : {0}\n [*] PAIS : {1}", c.getId(), c.getCountryName()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  PAIS NO ENCONTRADO");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void updateCountry() {
        List<Country> Countrys = countryService.getAllCountries();

        if (Countrys.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN PAIS REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL PAIS A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<Country> Country = countryService.getCountryById(findId);
            Country.ifPresentOrElse(
            c -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** ACTUALIZAR PAIS ***************");
                System.out.println(MessageFormat.format("[*] ID : {0}\n [*] PAIS : {1}", c.getId(), c.getCountryName()));

                System.out.println("[*] INGRESE EL NOMBRE DEL PAIS: ");
                String countryName = sc.nextLine();
            
                Country updatedCountry = new Country(c.getId(), countryName);
                countryService.updateCountry(updatedCountry);
                sc.nextLine();

                ConsoleUtils.limpiarConsola();
                System.out.println("[*] PAIS ACTUALIZADO CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("[!] PAIS NO ENCONTRADO");
                sc.nextLine();
            }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }


    public void deleteCountry() {
        List<Country> countries = countryService.getAllCountries();
        
        if (countries.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN PAIS REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL PAIS A ELIMINAR: ");
            String findId = sc.nextLine();

            Optional<Country> country = countryService.getCountryById(findId);
            country.ifPresentOrElse(
                c -> {
                    countryService.deleteCountry(findId);
                    System.out.println("[!] PAIS ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  PAIS NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        
        if (countries.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN PAIS REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            countryService.getAllCountries().forEach(c -> {
               System.out.println(MessageFormat.format("[*] ID : {0}\n [*] PAIS : {1}", c.getId(), c.getCountryName())); 
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}
