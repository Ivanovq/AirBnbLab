package mk.ukim.finki.airbnblab.service.application.impl;

import mk.ukim.finki.airbnblab.DTO.CreateHostDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayHostDTO;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.service.application.HostApplicationService;
import mk.ukim.finki.airbnblab.service.domain.CountryService;
import mk.ukim.finki.airbnblab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HostApplicationServiceImpl implements HostApplicationService {


    private final HostService hostService;
    private final CountryService countryService;
    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDTO> findAll() {
        return hostService.findAll().stream().map(i->DisplayHostDTO.from(i)).toList();
    }

    @Override
    public Optional<DisplayHostDTO> findById(Long id) {
        return hostService.findById(id).map(i->DisplayHostDTO.from(i));
    }

    @Override
    public Optional<DisplayHostDTO> save(CreateHostDTO hostDTO) {
        Optional<Country> country=countryService.findById(hostDTO.country());
        if(country.isPresent()) {
            return hostService.save(hostDTO.toHost(country.get())).map(DisplayHostDTO::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDTO> update(Long id, CreateHostDTO hostDTO) {
        Optional<Country> country=countryService.findById(hostDTO.country());
        return hostService.update(id,hostDTO.toHost(country.orElse(null))).map(DisplayHostDTO::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }
}
