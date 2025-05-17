package mk.ukim.finki.airbnblab.repository;

import mk.ukim.finki.airbnblab.model.ReservationList;
import mk.ukim.finki.airbnblab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReservationsListRepository extends JpaRepository<ReservationList,Long> {

    @Query("SELECT rl FROM ReservationList rl where rl.user= :user ")
    public Optional<ReservationList> findByUser(@Param("user") User user);

}
