package mk.ukim.finki.airbnblab.service.application.impl;

import mk.ukim.finki.airbnblab.DTO.CreateCountryDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayCountryDTO;
import mk.ukim.finki.airbnblab.service.application.CountryApplicationService;
import mk.ukim.finki.airbnblab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDTO> findAll() {
        return countryService.findAll().stream().map(DisplayCountryDTO::from).toList();
    }

    @Override
    public Optional<DisplayCountryDTO> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDTO::from);
    }

    @Override
    public Optional<DisplayCountryDTO> save(CreateCountryDTO countryDTO) {
        return countryService.save(countryDTO.toCountry()).map(DisplayCountryDTO::from);
    }
    @Override
    public Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO countryDTO) {
        return countryService.update(id,countryDTO.toCountry()).map(DisplayCountryDTO::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
