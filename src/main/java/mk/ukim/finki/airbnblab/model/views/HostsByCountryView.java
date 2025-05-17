package mk.ukim.finki.airbnblab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT * FROM public.hosts_by_country")
public class HostsByCountryView {
    @Id
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "num_hosts")
    private Integer numHosts;

    public HostsByCountryView() {
    }

    public HostsByCountryView(Long countryId, Integer numHosts) {
        this.countryId = countryId;
        this.numHosts = numHosts;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Integer getNumHosts() {
        return numHosts;
    }

    public void setNumHosts(Integer numHosts) {
        this.numHosts = numHosts;
    }
}