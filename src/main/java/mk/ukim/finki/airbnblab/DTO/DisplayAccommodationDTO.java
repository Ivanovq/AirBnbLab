package mk.ukim.finki.airbnblab.DTO;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDTO(Long id, String name, Category category, Long host, Integer numRooms) {

    public static DisplayAccommodationDTO from (Accommodation accommodation){
        return new DisplayAccommodationDTO(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }
    public List<DisplayAccommodationDTO> from (List<Accommodation> accommodations){
        return accommodations.stream().map(i->from(i)).collect(Collectors.toList());
    }


}
