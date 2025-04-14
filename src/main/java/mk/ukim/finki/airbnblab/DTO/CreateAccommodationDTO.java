package mk.ukim.finki.airbnblab.DTO;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import mk.ukim.finki.airbnblab.model.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDTO(String name,Category category,Long host, Integer numRooms) {


       public static CreateAccommodationDTO from (Accommodation accommodation)
       {
           return new CreateAccommodationDTO(
                   accommodation.getName(),
                   accommodation.getCategory(),
                   accommodation.getHost().getId(),
                   accommodation.getNumRooms()
           );
       }
       public static List<CreateAccommodationDTO> from (List<Accommodation> accommodations){
           return accommodations.stream().map(i->from(i)).collect(Collectors.toList());
       }
       public Accommodation toAccommodation(Host host){
           return new Accommodation(name,category,host,numRooms);
       }

}
