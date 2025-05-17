package mk.ukim.finki.airbnblab.model.views;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.stereotype.Controller;

@Entity
@Immutable
@Subselect("SELECT * FROM public.accommodations_by_host")
public class AccommodationsByHostView {
    @Id
    @Column(name = "host_id")
    private Long hostId;
    @Column(name = "num_accommodations")
    private Integer numAccommodations;

    public AccommodationsByHostView() {
    }

    public AccommodationsByHostView(Long hostId, Integer numAccommodations) {
        this.hostId = hostId;
        this.numAccommodations = numAccommodations;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Integer getNumAccommodations() {
        return numAccommodations;
    }

    public void setNumAccommodations(Integer numAccommodations) {
        this.numAccommodations = numAccommodations;
    }
}
