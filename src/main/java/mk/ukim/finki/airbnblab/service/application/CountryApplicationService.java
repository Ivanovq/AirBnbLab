package mk.ukim.finki.airbnblab.service.application;

import mk.ukim.finki.airbnblab.DTO.CreateCountryDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayCountryDTO;
import mk.ukim.finki.airbnblab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDTO> findAll();
    Optional<DisplayCountryDTO> findById(Long id);
    Optional<DisplayCountryDTO> save(CreateCountryDTO countryDTO);
    Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO countryDTO);
    void deleteById(Long id);
}
