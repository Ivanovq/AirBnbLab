package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.ReservationList;
import mk.ukim.finki.airbnblab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReservationsListRepository extends JpaRepository<ReservationList,Long> {
    public Optional<ReservationList> findByUser(User user);

}
