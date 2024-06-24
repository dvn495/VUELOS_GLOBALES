package com.vuelos_globales.Country.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.Optional;

import com.vuelos_globales.Country.application.CountryService;
import com.vuelos_globales.Country.domain.Country;

public class CountryConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final CountryService countryService;

    public CountryConsoleAdapter(CountryService countryService) {
        this.countryService = countryService;
    }

    public void createCountry() {
        System.out.println("[*] INGRESE EL ID DEL PAIS A CREAR: ");
        String id = sc.nextLine();

        System.out.println("[*] INGRESE EL NOMBRE DEL PAIS: ");
        String countryName = sc.nextLine();

        Country newCountry = new Country(id, countryName);
        countryService.createCountry(newCountry);
    }

        public void searchCountry() {
        System.out.println("[?] INGRESE EL ID DEL PAIS: ");
        String searchId = sc.nextLine();

        Optional<Country> Country = countryService.getCountryById(searchId);
        Country.ifPresentOrElse(
            p -> System.out.println(MessageFormat.format("*] ID : {0}\n [*] PAIS : {1}", p.getId(), p.getCountryName())),
            () -> System.out.println("[!] PAIS NO ENCONTRADO.")
            );
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
    }

    public void updateCountry() {
        System.out.println("[?] INGRESE EL ID DE LA CIUDAD: ");
        String findId = sc.nextLine();

        Optional<Country> country = countryService.getCountryById(findId);
        country.ifPresentOrElse(
            p -> {
                System.out.println(MessageFormat.format("[*] ID : {0}\n [*] PAIS : {1}", p.getId(), p.getCountryName()));
            },
            () -> {
                System.out.println("[!] PAIS NO ENCONTRADO.");
            }
            );
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
    }

    public void deleteCountry() {
        System.out.println("[?]  INGRESE EL ID DEL PAIS: ");
        String findId = sc.nextLine();

        Optional<Country> country = countryService.getCountryById(findId);
        country.ifPresentOrElse(
            p -> {
                countryService.deleteCountry(findId);
                System.out.println("[*] PAIS ELIMINADO CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                System.out.println("[!] PAIS NO ENCONTRADO.");
                sc.nextLine();
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
    }
}
