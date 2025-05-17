package mk.ukim.finki.airbnblab.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.airbnblab.model.views.HostsByCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostByCountryViewRepository extends JpaRepository<HostsByCountryView, Long>
{
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_by_country", nativeQuery = true)
    void refreshMaterializedView();

    List<HostsByCountryView> findAll();
}
