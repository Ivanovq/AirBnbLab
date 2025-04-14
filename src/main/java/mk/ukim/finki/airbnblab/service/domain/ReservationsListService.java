package mk.ukim.finki.airbnblab.service.domain;

import mk.ukim.finki.airbnblab.DTO.DisplayListDTO;
import mk.ukim.finki.airbnblab.model.ReservationList;
import mk.ukim.finki.airbnblab.model.exceptions.AccomodationAlreadyInListException;
import mk.ukim.finki.airbnblab.model.exceptions.NotEnoughRooms;

import java.util.Optional;

public interface ReservationsListService {

    public Optional<ReservationList> listAll(String username);
    Optional<ReservationList> addToList(String username,Long accId,int numRooms) throws AccomodationAlreadyInListException, NotEnoughRooms;

    Optional<ReservationList> confirmList(String username);
}
