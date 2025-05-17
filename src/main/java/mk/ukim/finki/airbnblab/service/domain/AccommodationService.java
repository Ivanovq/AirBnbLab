package mk.ukim.finki.airbnblab.service.domain;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import mk.ukim.finki.airbnblab.model.views.AccommodationsByHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(Accommodation accommodation);
    Optional<Accommodation> update(Long id,Accommodation accommodation);

     Optional<Accommodation> reserveRooms(Long id, int roomsToReserve);
     List<Accommodation> findByCategory(Category category,Long id);
    void deleteById(Long id);

    List<AccommodationsByHostView> findAccommodationsByHost();
    void refreshMaterializedView();

}
