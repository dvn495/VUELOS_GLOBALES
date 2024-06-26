package com.vuelos_globales.entities.Country.infrastructure;

import java.util.List;
import java.util.Optional;

import com.vuelos_globales.entities.Country.domain.Country;

public interface CountryRepository {
    void save(Country country);
    void update(Country country);
    Optional<Country> findById(String id);
    void delete(String id);
    List<Country> findAll();
}
