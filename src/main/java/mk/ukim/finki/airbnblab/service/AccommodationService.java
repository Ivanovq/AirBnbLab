package mk.ukim.finki.airbnblab.service;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(AccommodationDto accommodation);
    Optional<Accommodation> update(Long id,AccommodationDto accommodation);

     Optional<Accommodation> reserveRooms(Long id, int roomsToReserve);
    void deleteById(Long id);
}
