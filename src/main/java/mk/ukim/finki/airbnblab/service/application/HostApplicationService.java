package mk.ukim.finki.airbnblab.service.application;

import mk.ukim.finki.airbnblab.DTO.CreateHostDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayHostDTO;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.projections.HostProjection;
import mk.ukim.finki.airbnblab.model.views.HostsByCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {


    List<DisplayHostDTO> findAll();
    Optional<DisplayHostDTO> findById(Long id);
    Optional<DisplayHostDTO> save(CreateHostDTO hostDTO);
    Optional<DisplayHostDTO> update(Long id, CreateHostDTO hostDTO);
    void deleteById(Long id);
    List<HostsByCountryView> findHostsByCountry();
    List<HostProjection> getNamesAndSurnames();
    List<DisplayHostDTO> findByCountryId(Long countryId);

}
