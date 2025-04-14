package mk.ukim.finki.airbnblab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.airbnblab.DTO.CreateAccommodationDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayAccommodationDTO;
import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.Enumerations.Category;
import mk.ukim.finki.airbnblab.service.application.AccommodationApplicationService;
import mk.ukim.finki.airbnblab.service.domain.AccommodationService;
import mk.ukim.finki.airbnblab.service.domain.CountryService;
import mk.ukim.finki.airbnblab.service.domain.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation", description = "API за управување со сместувања")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;
    private final CountryService countryService;
    private final HostService hostService;

    public AccommodationController(AccommodationApplicationService accommodationService, CountryService countryService, HostService hostService) {
        this.accommodationApplicationService = accommodationService;
        this.countryService = countryService;
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "Превземи сите сместувања", description = "Ги враќа сите сместувања во системот")
    public List<DisplayAccommodationDTO> findAll() {
        return  accommodationApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Превземи сместување по ID", description = "Го враќа записот за сместување со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пронајдено сместување"),
            @ApiResponse(responseCode = "404", description = "Сместувањето не е пронајдено")
    })
    public ResponseEntity<DisplayAccommodationDTO> findById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Додај ново сместување", description = "Креира нов запис за сместување")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Сместувањето е успешно креирано"),
            @ApiResponse(responseCode = "400", description = "Грешка при креирање")
    })
    public ResponseEntity<DisplayAccommodationDTO> save(@RequestBody CreateAccommodationDTO accommodation) {
       return accommodationApplicationService.save(accommodation).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Измени постоечко сместување", description = "Ажурира запис за сместување со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно ажурирано сместување"),
            @ApiResponse(responseCode = "400", description = "Грешка при ажурирање"),
            @ApiResponse(responseCode = "404", description = "Сместувањето не е пронајдено")
    })
    public ResponseEntity<DisplayAccommodationDTO> update(@PathVariable Long id, @RequestBody CreateAccommodationDTO accommodation) {
        return accommodationApplicationService.update(id,accommodation).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Избриши сместување", description = "Го брише записот за сместување со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешно избришано сместување"),
            @ApiResponse(responseCode = "404", description = "Сместувањето не е пронајдено")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/reserve")
    @Operation(summary = "Резервирај соби", description = "Одзема од бројот на слободни соби за дадено сместување")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно резервирано сместување"),
            @ApiResponse(responseCode = "400", description = "Недоволно слободни соби"),
            @ApiResponse(responseCode = "404", description = "Сместувањето не е пронајдено")
    })
    public ResponseEntity<DisplayAccommodationDTO> reserveAccommodation(@PathVariable Long id, @RequestParam int roomsToReserve) {
       return accommodationApplicationService.reserveRooms(id,roomsToReserve).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Понудени сместувања", description = "Прикажува листа од сместувања од ист тип.")
    @GetMapping("/recommended/{id}")
    public ResponseEntity<List<DisplayAccommodationDTO>> findByType(@PathVariable Long id)
    {
        Category category=accommodationApplicationService.findById(id).get().category();
        List<DisplayAccommodationDTO> list=accommodationApplicationService.findByCategory(category,id).stream().toList();
        return ResponseEntity.ok(list);
    }


}
