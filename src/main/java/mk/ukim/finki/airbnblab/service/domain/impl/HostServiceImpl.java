package mk.ukim.finki.airbnblab.service.domain.impl;

import mk.ukim.finki.airbnblab.events.HostEvent;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.projections.HostProjection;
import mk.ukim.finki.airbnblab.model.views.HostsByCountryView;
import mk.ukim.finki.airbnblab.repository.HostByCountryViewRepository;
import mk.ukim.finki.airbnblab.repository.HostRepository;
import mk.ukim.finki.airbnblab.service.domain.CountryService;
import mk.ukim.finki.airbnblab.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostByCountryViewRepository hostsByCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostByCountryViewRepository hostsByCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsByCountryViewRepository = hostsByCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
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
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();
        if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
            savedHost = Optional.of(
                    hostRepository.save(new Host(host.getName(), host.getSurname(),
                            countryService.findById(host.getCountry().getId()).get())));
            this.applicationEventPublisher.publishEvent(new HostEvent(host));
        }
        return savedHost;    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id)
                .map(existingProduct -> {
                    if (host.getName() != null) {
                        existingProduct.setName(host.getName());
                    }
                    if (host.getSurname() != null) {
                        existingProduct.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
                        existingProduct.setCountry(countryService.findById(host.getCountry().getId()).get());
                    }
                    Host updatedHost = hostRepository.save(existingProduct);
                    this.applicationEventPublisher.publishEvent(new HostEvent(host));
                    return updatedHost;
                });
    }

    @Override
    public void deleteById(Long id) {
        Host host=this.findById(id).orElseThrow(RuntimeException::new);
        hostRepository.deleteById(id);
        this.applicationEventPublisher.publishEvent(new HostEvent(host));
    }

    @Override
    public void refreshMaterializedView() {
        hostsByCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostsByCountryView> findHostsByCountry() {
        return hostsByCountryViewRepository.findAll();
    }


    @Override
    public List<HostProjection> getNamesAndSurnames() {
        return hostRepository.getNameAndSurnameProjection();
    }

    @Override
    public List<Host> findByCountryId(Long countryId) {
        return hostRepository.findAllByCountryId(countryId);
    }
}
