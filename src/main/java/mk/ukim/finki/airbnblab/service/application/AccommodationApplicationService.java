package mk.ukim.finki.airbnblab.service.application;

import mk.ukim.finki.airbnblab.DTO.CreateAccommodationDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayAccommodationDTO;
import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {

    List<DisplayAccommodationDTO> findAll();
    Optional<DisplayAccommodationDTO> findById(Long id);
    Optional<DisplayAccommodationDTO> save(CreateAccommodationDTO accommodationDTO);
    Optional<DisplayAccommodationDTO> update(Long id,CreateAccommodationDTO accommodationDTO);

    Optional<DisplayAccommodationDTO> reserveRooms(Long id, int roomsToReserve);
    List<DisplayAccommodationDTO> findByCategory(Category category, Long id);
    void deleteById(Long id);

}
