package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {

    @Query("select a from Accommodation a where a.category =:category and a.id =:id")

    List<Accommodation> findByCategoryAndIdNot(@Param("category") Category category,@Param("id") Long id);


}
