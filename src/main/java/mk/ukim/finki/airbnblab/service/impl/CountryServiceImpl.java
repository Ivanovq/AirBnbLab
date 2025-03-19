package mk.ukim.finki.airbnblab.service.impl;

import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.dto.CountryDto;
import mk.ukim.finki.airbnblab.repository.CountryRepository;
import mk.ukim.finki.airbnblab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto country) {
        return Optional.of(countryRepository.save(new Country(country.getName(), country.getContinent())));
    }

    @Override
    public Optional<Country> update(Long id, CountryDto country) {
        return countryRepository.findById(id).map(existing->{
            if(country.getName()!=null)
            {
                existing.setName(country.getName());
            }
            if(country.getContinent()!=null)
            {
                existing.setContinent(country.getContinent());
            }
            return countryRepository.save(existing);
        });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
