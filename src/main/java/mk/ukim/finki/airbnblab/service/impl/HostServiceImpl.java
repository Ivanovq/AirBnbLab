package mk.ukim.finki.airbnblab.service.impl;

import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.dto.HostDto;
import mk.ukim.finki.airbnblab.repository.HostRepository;
import mk.ukim.finki.airbnblab.service.CountryService;
import mk.ukim.finki.airbnblab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(HostDto host) {
        return Optional.of(hostRepository.save(new Host(host.getName(), host.getSurname(),countryService.findById(host.getCountry()).get())));
    }

    @Override
    public Optional<Host> update(Long id, HostDto host) {
        return hostRepository.findById(id).map(existing->{
            if(host.getCountry()!=null)
            {
                existing.setCountry(countryService.findById(host.getCountry()).get());
            }
            if(host.getName()!=null)
            {
                existing.setName(host.getName());
            }
            if(host.getSurname()!=null)
            {
                existing.setSurname(host.getSurname());
            }
            return hostRepository.save(existing);
        });
    }

    @Override
    public void deleteById(Long id) {

        hostRepository.deleteById(id);
    }
}
