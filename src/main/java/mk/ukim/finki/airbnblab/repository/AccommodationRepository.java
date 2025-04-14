package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {

    List<Accommodation> findByCategoryAndIdNot(Category category,Long id);
}
