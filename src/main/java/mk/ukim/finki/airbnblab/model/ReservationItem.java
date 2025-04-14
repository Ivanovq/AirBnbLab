package mk.ukim.finki.airbnblab.model;


import jakarta.persistence.*;

@Entity
public class ReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Accommodation accommodation;

    int numRooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public ReservationList getReservationList() {
        return reservationList;
    }

    public void setReservationList(ReservationList reservationList) {
        this.reservationList = reservationList;
    }

    @ManyToOne
    ReservationList reservationList;

    public ReservationItem(Accommodation accommodation, int numRooms) {
        this.accommodation=accommodation;
        this.numRooms=numRooms;
    }

    public ReservationItem() {

    }
}
