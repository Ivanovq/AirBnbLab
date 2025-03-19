package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
}
