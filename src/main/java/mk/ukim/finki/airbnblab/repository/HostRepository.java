package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
}
