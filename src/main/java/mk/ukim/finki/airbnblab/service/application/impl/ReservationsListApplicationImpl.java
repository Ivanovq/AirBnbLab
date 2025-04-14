package mk.ukim.finki.airbnblab.service.application.impl;

import mk.ukim.finki.airbnblab.DTO.DisplayListDTO;
import mk.ukim.finki.airbnblab.model.exceptions.AccomodationAlreadyInListException;
import mk.ukim.finki.airbnblab.model.exceptions.NotEnoughRooms;
import mk.ukim.finki.airbnblab.service.application.ReservationsListApplicationService;
import mk.ukim.finki.airbnblab.service.domain.ReservationsListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationsListApplicationImpl implements ReservationsListApplicationService {

    private final ReservationsListService reservationsListService;

    public ReservationsListApplicationImpl(ReservationsListService reservationsListService) {
        this.reservationsListService = reservationsListService;
    }

    @Override
    public Optional<DisplayListDTO> listAllByUser(String username) {
        return reservationsListService.listAll(username).map(DisplayListDTO::from);
    }

    @Override
    public Optional<DisplayListDTO> addToList(String username, Long id,int numRooms) throws AccomodationAlreadyInListException, NotEnoughRooms {
        return reservationsListService.addToList(username,id,numRooms).map(DisplayListDTO::from);
    }

    @Override
    public Optional<DisplayListDTO> confirm(String username) {
        return reservationsListService.confirmList(username).map(DisplayListDTO::from);
    }
}
