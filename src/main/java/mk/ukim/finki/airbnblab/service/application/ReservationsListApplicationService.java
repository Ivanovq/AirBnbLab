package mk.ukim.finki.airbnblab.service.application;

import mk.ukim.finki.airbnblab.DTO.DisplayListDTO;
import mk.ukim.finki.airbnblab.model.exceptions.AccomodationAlreadyInListException;
import mk.ukim.finki.airbnblab.model.exceptions.NotEnoughRooms;

import java.util.List;
import java.util.Optional;

public interface ReservationsListApplicationService {

    public Optional<DisplayListDTO> listAllByUser(String username);
    Optional<DisplayListDTO> addToList(String username,Long id,int numRooms) throws AccomodationAlreadyInListException, NotEnoughRooms;
    Optional<DisplayListDTO> confirm(String username);
}
