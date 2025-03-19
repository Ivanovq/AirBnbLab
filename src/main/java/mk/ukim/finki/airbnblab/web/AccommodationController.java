package mk.ukim.finki.airbnblab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.airbnblab.model.Accommodation;
import mk.ukim.finki.airbnblab.model.dto.AccommodationDto;
import mk.ukim.finki.airbnblab.service.AccommodationService;
import mk.ukim.finki.airbnblab.service.CountryService;
import mk.ukim.finki.airbnblab.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation", description = "API за управување со сместувања")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final CountryService countryService;
    private final HostService hostService;

    public AccommodationController(AccommodationService accommodationService, CountryService countryService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.countryService = countryService;
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "Превземи сите сместувања", description = "Ги враќа сите сместувања во системот")
    public ResponseEntity<List<Accommodation>> findAll() {
        List<Accommodation> accommodations = accommodationService.findAll();
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Превземи сместување по ID", description = "Го враќа записот за сместување со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пронајдено сместување"),
            @ApiResponse(responseCode = "404", description = "Сместувањето не е пронајдено")
    })
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        Optional<Accommodation> accommodation = accommodationService.findById(id);
        return accommodation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Додај ново сместување", description = "Креира нов запис за сместување")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Сместувањето е успешно креирано"),
            @ApiResponse(responseCode = "400", description = "Грешка при креирање")
    })
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodation) {
        Optional<Accommodation> savedAccommodation = accommodationService.save(accommodation);
        return savedAccommodation.map(a -> ResponseEntity.status(201).body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Измени постоечко сместување", description = "Ажурира запис за сместување со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно ажурирано сместување"),
            @ApiResponse(responseCode = "400", description = "Грешка при ажурирање"),
            @ApiResponse(responseCode = "404", description = "Сместувањето не е пронајдено")
    })
    public ResponseEntity<Accommodation> update(@PathVariable Long id, @RequestBody AccommodationDto accommodation) {
        Optional<Accommodation> updatedAccommodation = accommodationService.update(id, accommodation);
        return updatedAccommodation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Избриши сместување", description = "Го брише записот за сместување со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешно избришано сместување"),
            @ApiResponse(responseCode = "404", description = "Сместувањето не е пронајдено")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
