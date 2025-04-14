package mk.ukim.finki.airbnblab.service.application.impl;

import mk.ukim.finki.airbnblab.DTO.CreateAccommodationDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayAccommodationDTO;
import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.service.application.AccommodationApplicationService;
import mk.ukim.finki.airbnblab.service.domain.AccommodationService;
import mk.ukim.finki.airbnblab.service.domain.CountryService;
import mk.ukim.finki.airbnblab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final CountryService countryService;
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(CountryService countryService, AccommodationService accommodationService, HostService hostService) {
        this.countryService = countryService;
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }


    @Override
    public List<DisplayAccommodationDTO> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccommodationDTO::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDTO> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDTO::from);
    }

    @Override
    public Optional<DisplayAccommodationDTO> save(CreateAccommodationDTO accommodationDTO) {
        Optional<Host> host=hostService.findById(accommodationDTO.host());
        if(host.isPresent())
        {
            return accommodationService.save(accommodationDTO.toAccommodation(host.get())).map(DisplayAccommodationDTO::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO accommodationDTO) {
        Optional<Host> host=hostService.findById(accommodationDTO.host());
        return accommodationService.update(id,accommodationDTO.toAccommodation(host.orElse(null))).map(DisplayAccommodationDTO::from);
    }

    @Override
    public Optional<DisplayAccommodationDTO> reserveRooms(Long id, int roomsToReserve) {
        Optional<Accommodation> acc= accommodationService.findById(id);

        if (acc.isPresent()) {
            Accommodation accommodation = acc.get();

            if (accommodation.getNumRooms() >= roomsToReserve) {
                accommodation.setNumRooms(accommodation.getNumRooms() - roomsToReserve);
                return acc.map(DisplayAccommodationDTO::from);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<DisplayAccommodationDTO> findByCategory(Category category, Long id) {
        return accommodationService.findByCategory(category,id).stream().map(DisplayAccommodationDTO::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }


}
