package mk.ukim.finki.airbnblab.service.impl;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.dto.AccommodationDto;
import mk.ukim.finki.airbnblab.repository.AccommodationRepository;
import mk.ukim.finki.airbnblab.service.AccommodationService;
import mk.ukim.finki.airbnblab.service.CountryService;
import mk.ukim.finki.airbnblab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

private final HostService hostService;
private final AccommodationRepository  accommodationRepository;

    public AccommodationServiceImpl(HostService hostService, CountryService countryService, AccommodationRepository accommodationRepository) {
        this.hostService = hostService;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if(accommodation.getHost()!=null && hostService.findById(accommodation.getHost()).isPresent() )
         return Optional.of(accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),hostService.findById(accommodation.getHost()).get(),accommodation.getNumRooms())));

    return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return accommodationRepository.findById(id).map(existing->{
            if(accommodation.getHost()!=null&&hostService.findById(accommodation.getHost()).isPresent())
            {
                existing.setHost(hostService.findById(accommodation.getHost()).get());
            }
            if(accommodation.getCategory()!=null)
            {
                existing.setCategory(accommodation.getCategory());
            }
            if(accommodation.getName()!=null)
            {
                existing.setName(accommodation.getName());
            }
            if(accommodation.getNumRooms()!=null)
            {
                existing.setNumRooms(accommodation.getNumRooms());
            }
            return accommodationRepository.save(existing);
        });
    }

    @Override
    public void deleteById(Long id) {

        accommodationRepository.deleteById(id);
    }

}
