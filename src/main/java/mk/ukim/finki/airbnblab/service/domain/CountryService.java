package mk.ukim.finki.airbnblab.service.domain;

import mk.ukim.finki.airbnblab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();
     Optional<Country> findById(Long id);
    Optional<Country> save(Country country);
    Optional<Country> update(Long id, Country country);
    void deleteById(Long id);
}
