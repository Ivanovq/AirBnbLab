package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {

    @Query("select h.name AS name, h.surname as surname from Host h")
    List<HostProjection> getNameAndSurnameProjection();
    List<Host> findAllByCountryId(Long countryId);
}
