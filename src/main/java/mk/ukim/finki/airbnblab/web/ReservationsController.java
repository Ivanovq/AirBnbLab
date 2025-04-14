package mk.ukim.finki.airbnblab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.airbnblab.DTO.DisplayAccommodationDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayListDTO;
import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.User;
import mk.ukim.finki.airbnblab.model.exceptions.AccomodationAlreadyInListException;
import mk.ukim.finki.airbnblab.model.exceptions.NotEnoughRooms;
import mk.ukim.finki.airbnblab.service.application.AccommodationApplicationService;
import mk.ukim.finki.airbnblab.service.application.ReservationsListApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Shopping Cart API", description = "Endpoints for managing the shopping cart")


public class ReservationsController {

    private final ReservationsListApplicationService listApplicationService;
    private final AccommodationApplicationService accommodationApplicationService;

    public ReservationsController(ReservationsListApplicationService listApplicationService, AccommodationApplicationService accommodationApplicationService) {
        this.listApplicationService = listApplicationService;
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Operation(
            summary = "Get active shopping cart",
            description = "Retrieves the active shopping cart for the logged-in user"
    )

    @GetMapping
    public ResponseEntity<DisplayListDTO> listAll(HttpServletRequest request) {
        String username = request.getRemoteUser();
        return listApplicationService.listAllByUser(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/add-reservation/{id}")
    public ResponseEntity<?> addAccomodationToReservationList(@PathVariable Long id, Authentication authentication,@Parameter(description = "Број на соби кои се бараат за резервација") @RequestParam int numRooms) throws AccomodationAlreadyInListException, NotEnoughRooms {
        User user= (User) authentication.getPrincipal();
        try {
            return listApplicationService.addToList(user.getUsername(), id, numRooms).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
        catch (NotEnoughRooms e)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Сместувањето нема доволно слободни соби."));
        }
    }


    @PostMapping("/api/confirm-reservation")
    public ResponseEntity<DisplayListDTO> confirmReservation(Authentication authentication)
    {
        User user= (User) authentication.getPrincipal();
        return listApplicationService.confirm(user.getUsername()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
