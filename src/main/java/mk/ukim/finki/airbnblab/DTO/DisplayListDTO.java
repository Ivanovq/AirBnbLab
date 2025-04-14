package mk.ukim.finki.airbnblab.DTO;

import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.ReservationListStatus;
import mk.ukim.finki.airbnblab.model.ReservationItem;
import mk.ukim.finki.airbnblab.model.ReservationList;
import mk.ukim.finki.airbnblab.model.User;

import java.util.List;

public record DisplayListDTO(Long id, List<ReservationItem> items, User user, ReservationListStatus status) {

    public static DisplayListDTO from (ReservationList list)
    {
        return new DisplayListDTO(
                list.getId(),
                list.getItems(),
                list.getUser(),
                list.getStatus()
        );
    }
}
