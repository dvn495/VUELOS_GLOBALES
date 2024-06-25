package com.vuelos_globales.Country.application;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.Country.domain.Country;
import com.vuelos_globales.Country.infrastructure.CountryRepository;

public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void createCountry(Country country) {
        countryRepository.save(country);
    }

    public void updateCountry(Country country) {
        countryRepository.update(country);
    }

    public Optional<Country> getCountryById(String id) {
        return countryRepository.findById(id);
    }
    
    public void deleteCountry(String id) {
        countryRepository.delete(id);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }


}
