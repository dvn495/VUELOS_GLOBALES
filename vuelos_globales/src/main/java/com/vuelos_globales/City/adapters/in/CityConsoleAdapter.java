package com.vuelos_globales.City.adapters.in;

import java.util.Scanner;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import com.vuelos_globales.ConsoleUtils;
import com.vuelos_globales.City.application.CityService;
import com.vuelos_globales.City.domain.City;

public class CityConsoleAdapter {
    Scanner sc = new Scanner(System.in);

    private final CityService cityService; 

    public CityConsoleAdapter(CityService cityService) {
        this.cityService = cityService;
    }

    public void createCity() {
        String rta = "S";

        while (rta.equalsIgnoreCase("S")) {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** REGISTRAR CIUDAD ***************");
            System.out.println("[*] INGRESE EL ID DE LA CIUDAD A CREAR: ");
            String id = sc.nextLine();
            Optional<City> city = cityService.getCityById(id);
            city.ifPresentOrElse(
                c -> {
                    System.out.println(MessageFormat.format("[!] EL ID (0) YA ESTA OCUPADO.", c.getId()));
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** REGISTRAR CIUDAD ***************");
            
                    System.out.println("[*] INGRESE EL NOMBRE DE LA CIUDAD: ");
                    String cityName = sc.nextLine();
            
                    System.out.println("[*] INGRESE EL ID DEL PAIS DE LA CIUDAD: ");
                    String countryCity = sc.nextLine();
                
                    City newCity = new City(id, cityName, countryCity);
                    cityService.createCity(newCity);
                });

            System.out.println("[?] DESEA AÑADIR OTRO CIUDAD? [S] - SI | [INGRESE CUALQUIER TECLA] - NO");
            rta = sc.nextLine();
        }
    }

    public void searchCity() {
        List<City> citys = cityService.getAllCities();
        
        if (citys.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CIUDAD REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("*************** BUSCAR CIUDAD ***************");
            System.out.println("[?] INGRESE EL ID DEL CIUDAD A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<City> city = cityService.getCityById(findId);
            city.ifPresentOrElse(
                c -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("*************** CIUDAD ***************");
                    System.out.println(MessageFormat.format("*] ID : {0}\n [*] CIUDAD : {1}\n[*] PAIS : {2}", c.getId(), c.getCityName(), c.getCountryCity()));
                    sc.nextLine();
                },
                () -> {
                    ConsoleUtils.limpiarConsola();
                    System.out.println("[!]  CIUDAD NO ENCONTRADA");
                    sc.nextLine();
                });
                ConsoleUtils.limpiarConsola();
                System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
                sc.nextLine();
        }
    }

    public void updateCity() {
        List<City> cities = cityService.getAllCities();

        if (cities.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CLIENTE REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL CLIENTE A BUSCAR: ");
            String findId = sc.nextLine();

            Optional<City> city = cityService.getCityById(findId);
            city.ifPresentOrElse(
            c -> {
                ConsoleUtils.limpiarConsola();
                System.out.println("*************** ACTUALIZAR CLIENTE ***************");
                System.out.println(MessageFormat.format("*] ID : {0}\n [*] CIUDAD : {1}\n[*] PAIS : {2}", c.getId(), c.getCityName(), c.getCountryCity()));

                System.out.println("[*] INGRESE EL NOMBRE DE LA CIUDAD: ");
                String cityName = sc.nextLine();
        
                System.out.println("[*] INGRESE EL ID DEL PAIS DE LA CIUDAD: ");
                String countryCity = sc.nextLine();
            
                City updatedCity = new City(c.getId(), cityName, countryCity);
                cityService.updateCity(updatedCity);
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

    public void deleteCity() {
        List<City> cities = cityService.getAllCities();
        
        if (cities.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUN CIUDAD REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            System.out.println("[?] INGRESE EL ID DEL CIUDAD A ELIMINAR: ");
            String findId = sc.nextLine();

            Optional<City> city = cityService.getCityById(findId);
            city.ifPresentOrElse(
                c -> {
                    cityService.deleteCity(findId);
                    System.out.println("[!] CIUDAD ELIMINADO CORRECTAMENTE.");
                    sc.nextLine();
                },
                () -> {
                    System.out.println("[!]  CIUDAD NO ENCONTRADO");
                }
            );
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }

    public void getAllCitys() {
        List<City> cities = cityService.getAllCities();
        
        if (cities.isEmpty()) {
            ConsoleUtils.limpiarConsola();
            System.out.println("[!] NO HAY NINGUNA CIUDAD REGISTRADO");
            sc.nextLine();
        } else {
            ConsoleUtils.limpiarConsola();
            cityService.getAllCities().forEach(c -> {
               System.out.println(MessageFormat.format("*] ID : {0}\n [*] CIUDAD : {1}\n[*] PAIS : {2}", c.getId(), c.getCityName(), c.getCountryCity())); 
            });
            System.out.println("[*]  PRESIONE CUALQUIER TECLA PARA CONTINUAR...");
            sc.nextLine();
        }
    }
}
