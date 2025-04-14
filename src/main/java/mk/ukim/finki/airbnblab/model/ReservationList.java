package mk.ukim.finki.airbnblab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.airbnblab.model.Enumerations.ReservationListStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ReservationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User user ;
    @OneToMany( cascade = CascadeType.ALL)
    List<ReservationItem> items;
    @Enumerated(EnumType.STRING)
    ReservationListStatus status;

    public ReservationList() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public List<ReservationItem> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReservationListStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationListStatus status) {
        this.status = status;
    }

    public ReservationList(User user) {
        this.user = user;
        this.items=new ArrayList<>();
        this.setStatus(ReservationListStatus.CREATED);
    }
}
