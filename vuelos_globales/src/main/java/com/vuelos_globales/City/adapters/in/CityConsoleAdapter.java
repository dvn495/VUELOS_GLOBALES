package com.vuelos_globales.City.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.Optional;

import com.vuelos_globales.City.application.CityService;
import com.vuelos_globales.City.domain.City;

public class CityConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final CityService cityService; 

    public CityConsoleAdapter(CityService cityService) {
        this.cityService = cityService;
    }

    public void createCity() {
        System.out.println("[*] INGRESE EL ID DE LA CIUDAD A CREAR: ");
        String id = sc.nextLine();

        System.out.println("[*] INGRESE EL NOMBRE DE LA CIUDAD: ");
        String cityName = sc.nextLine();

        System.out.println("[*] INGRESE EL ID DEL PAIS DE LA CIUDAD: ");
        String countryCity = sc.nextLine();
    
        City newCity = new City(id, cityName, countryCity);
        cityService.createCity(newCity);
    }

    public void searchCity() {
        System.out.println("[?] INGRESE EL ID DE LA CIUDAD: ");
        String searchId = sc.nextLine();

        Optional<City> city = cityService.getCityById(searchId);
        city.ifPresentOrElse(
            c -> System.out.println(MessageFormat.format("*] ID : {0}\n [*] CIUDAD : {1}\n[*] PAIS : {2}", c.getId(), c.getCityName(), c.getCountryCity())),
            () -> System.out.println("[!] AEREOPUERTO NO ENCONTRADO.")
            );
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
    }

    public void updateCity() {
        System.out.println("[?] INGRESE EL ID DE LA CIUDAD: ");
        String findId = sc.nextLine();

        Optional<City> city = cityService.getCityById(findId);
        city.ifPresentOrElse(
            c -> {
                System.out.println(MessageFormat.format("[*] ID : {0}\n [*] CIUDAD : {1}\n[*] PAIS : {2}", c.getId(), c.getCityName(), c.getCountryCity()));
            },
            () -> {
                System.out.println("[!] AEREOPUERTO NO ENCONTRADO.");
            }
            );
            System.out.println("[*] PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
    }

    public void deleteCity() {
        System.out.println("[?]  INGRESE EL ID DE LA CIUDAD: ");
        String findId = sc.nextLine();

        Optional<City> city = cityService.getCityById(findId);
        city.ifPresentOrElse(
            c -> {
                cityService.deleteCity(findId);
                System.out.println("[*] CIUDAD ELIMINADA CORRECTAMENTE.");
                sc.nextLine();
            },
            () -> {
                System.out.println("[!] AEREOPUERTO NO ENCONTRADO.");
                sc.nextLine();
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
    }
}
