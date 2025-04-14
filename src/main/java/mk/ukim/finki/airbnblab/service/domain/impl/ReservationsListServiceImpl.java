package mk.ukim.finki.airbnblab.service.domain.impl;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.ReservationListStatus;
import mk.ukim.finki.airbnblab.model.ReservationItem;
import mk.ukim.finki.airbnblab.model.ReservationList;
import mk.ukim.finki.airbnblab.model.User;
import mk.ukim.finki.airbnblab.model.exceptions.AccomodationAlreadyInListException;
import mk.ukim.finki.airbnblab.model.exceptions.AccomodationNotFoundException;
import mk.ukim.finki.airbnblab.model.exceptions.NotEnoughRooms;
import mk.ukim.finki.airbnblab.repository.ReservationsListRepository;
import mk.ukim.finki.airbnblab.repository.UserRepository;
import mk.ukim.finki.airbnblab.service.domain.AccommodationService;
import mk.ukim.finki.airbnblab.service.domain.ReservationsListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationsListServiceImpl implements ReservationsListService {

    private final ReservationsListRepository reservationsListRepository;
    private final UserRepository userRepository;
    private final AccommodationService accommodationService;
    public ReservationsListServiceImpl(ReservationsListRepository reservationsListRepository, UserRepository userRepository, AccommodationService accommodationService) {
        this.reservationsListRepository = reservationsListRepository;
        this.userRepository = userRepository;
        this.accommodationService = accommodationService;
    }

    @Override

    public Optional<ReservationList> listAll(String username) {
        User user=userRepository.findByUsername(username).orElseGet(null);
        return Optional.of(reservationsListRepository.findByUser(user).orElseGet(() -> reservationsListRepository.save(new ReservationList(user))));
    }

    @Override
    public Optional<ReservationList> addToList(String username, Long accId,int numRooms) throws AccomodationAlreadyInListException, NotEnoughRooms {
        if(listAll(username).isPresent()){
            ReservationList reservationList=listAll(username).get();
            Accommodation accommodation=accommodationService.findById(accId).orElseThrow(()->new AccomodationNotFoundException(accId));

            if(accommodation.getNumRooms()<numRooms)
            {
                throw new NotEnoughRooms();
            }
            reservationList.getItems().add(new ReservationItem(accommodation,numRooms));
            return Optional.of(reservationsListRepository.save(reservationList));

        }
        return Optional.empty();
    }

    public Optional<ReservationList> confirmList(String username)
    {
        Optional<User> user=userRepository.findByUsername(username);
        Optional<ReservationList> list=reservationsListRepository.findByUser(user.get());

        for (ReservationItem item:list.get().getItems()
             ) {
                Accommodation ac=item.getAccommodation();
                if(ac.getNumRooms()>=item.getNumRooms())
                ac.setNumRooms(ac.getNumRooms()-item.getNumRooms());

        }
        list.get().setStatus(ReservationListStatus.CONFIRMED);
        return list;

    }

}
