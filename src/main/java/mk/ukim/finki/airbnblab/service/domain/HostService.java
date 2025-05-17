package mk.ukim.finki.airbnblab.service.domain;

import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.projections.HostProjection;
import mk.ukim.finki.airbnblab.model.views.HostsByCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {

    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> save(Host host);
    Optional<Host> update(Long id, Host host);
    void deleteById(Long id);

    void refreshMaterializedView();

    List<HostsByCountryView> findHostsByCountry();
    List<HostProjection> getNamesAndSurnames();

    List<Host> findByCountryId(Long countryId);
}
