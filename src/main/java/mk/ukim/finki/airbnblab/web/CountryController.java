package mk.ukim.finki.airbnblab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.dto.CountryDto;
import mk.ukim.finki.airbnblab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country", description = "API за управување со земји")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Превземи сите земји", description = "Ги враќа сите земји во системот")
    public ResponseEntity<List<Country>> findAll() {
        List<Country> countries = countryService.findAll();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Превземи земја по ID", description = "Го враќа записот за земја со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пронајдена земја"),
            @ApiResponse(responseCode = "400", description = "Грешен барање"),
            @ApiResponse(responseCode = "404", description = "Земјата не е пронајдена")
    })
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        Optional<Country> country = countryService.findById(id);
        return country.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Додај нова земја", description = "Креира нов запис за земја")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Земјата е успешно креирана"),
            @ApiResponse(responseCode = "400", description = "Грешка при креирање")
    })
    public ResponseEntity<Country> save(@RequestBody CountryDto country) {
        Optional<Country> savedCountry = countryService.save(country);
        return savedCountry.map(c -> ResponseEntity.status(201).body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Измени постоечка земја", description = "Ажурира запис за земја со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно ажурирана земја"),
            @ApiResponse(responseCode = "400", description = "Грешка при ажурирање"),
            @ApiResponse(responseCode = "404", description = "Земјата не е пронајдена")
    })
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody CountryDto country) {
        Optional<Country> updatedCountry = countryService.update(id, country);
        return updatedCountry.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
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
