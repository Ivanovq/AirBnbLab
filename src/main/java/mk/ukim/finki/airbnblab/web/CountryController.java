package mk.ukim.finki.airbnblab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.airbnblab.DTO.CreateCountryDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayCountryDTO;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.service.application.CountryApplicationService;
import mk.ukim.finki.airbnblab.service.domain.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country", description = "API за управување со земји")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Превземи сите земји", description = "Ги враќа сите земји во системот")
    public List<DisplayCountryDTO> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Превземи земја по ID", description = "Го враќа записот за земја со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пронајдена земја"),
            @ApiResponse(responseCode = "400", description = "Грешен барање"),
            @ApiResponse(responseCode = "404", description = "Земјата не е пронајдена")
    })
    public ResponseEntity<DisplayCountryDTO> findById(@PathVariable Long id) {
        return countryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Додај нова земја", description = "Креира нов запис за земја")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Земјата е успешно креирана"),
            @ApiResponse(responseCode = "400", description = "Грешка при креирање")
    })
    public ResponseEntity<DisplayCountryDTO> save(@RequestBody CreateCountryDTO country) {
        return countryService.save(country).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Измени постоечка земја", description = "Ажурира запис за земја со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно ажурирана земја"),
            @ApiResponse(responseCode = "400", description = "Грешка при ажурирање"),
            @ApiResponse(responseCode = "404", description = "Земјата не е пронајдена")
    })
    public ResponseEntity<DisplayCountryDTO> update(@PathVariable Long id, @RequestBody CreateCountryDTO country) {
        return countryService.update(id,country).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Избриши земја", description = "Го брише записот за земја со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешно избришана земја"),
            @ApiResponse(responseCode = "404", description = "Земјата не е пронајдена")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
