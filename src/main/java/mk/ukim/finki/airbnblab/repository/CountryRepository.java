package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
