package mk.ukim.finki.airbnblab.service;

import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();
     Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto country);
    Optional<Country> update(Long id, CountryDto country);
    void deleteById(Long id);
}
