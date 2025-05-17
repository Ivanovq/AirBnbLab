package mk.ukim.finki.airbnblab.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.airbnblab.model.views.AccommodationsByHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationsByHostViewRepository  extends JpaRepository<AccommodationsByHostView,Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_by_host",nativeQuery = true)
    void refreshMaterializedView();
    List<AccommodationsByHostView> findAll();

}

