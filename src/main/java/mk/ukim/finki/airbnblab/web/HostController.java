package mk.ukim.finki.airbnblab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.airbnblab.DTO.CreateHostDTO;
import mk.ukim.finki.airbnblab.DTO.DisplayHostDTO;
import mk.ukim.finki.airbnblab.model.Country;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.projections.HostProjection;
import mk.ukim.finki.airbnblab.model.views.HostsByCountryView;
import mk.ukim.finki.airbnblab.service.application.HostApplicationService;
import mk.ukim.finki.airbnblab.service.domain.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host", description = "API за управување со домаќини")
public class HostController {

    private final HostApplicationService hostService;

    public HostController(HostApplicationService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "Превземи сите домаќини", description = "Ги враќа сите домаќини во системот")
    public List<DisplayHostDTO> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "Get hosts by country", description = "Retrieves a list of countries and number of hosts.")
    @GetMapping("/by-country")
    public List<HostsByCountryView> findHostsByCountry() {
        return hostService.findHostsByCountry();
    }

    @Operation(summary = "Get names and surnames of hosts", description = "Retrieves a list of hosts with their names and surnames.")
    @GetMapping("/names")
    public List<HostProjection> getNamesAndSurnamesForHosts() {
        return hostService.getNamesAndSurnames();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Превземи домаќин по ID", description = "Го враќа записот за домаќин со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пронајден домаќин"),
            @ApiResponse(responseCode = "404", description = "Домаќинот не е пронајден")
    })
    public ResponseEntity<DisplayHostDTO> findById(@PathVariable Long id) {
        return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Додај нов домаќин", description = "Креира нов запис за домаќин")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Домаќинот е успешно креиран"),
            @ApiResponse(responseCode = "400", description = "Грешка при креирање")
    })
    public ResponseEntity<DisplayHostDTO> save(@RequestBody CreateHostDTO host) {
        return hostService.save(host).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Измени постоечки домаќин", description = "Ажурира запис за домаќин со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно ажуриран домаќин"),
            @ApiResponse(responseCode = "400", description = "Грешка при ажурирање"),
            @ApiResponse(responseCode = "404", description = "Домаќинот не е пронајден")
    })
    public ResponseEntity<DisplayHostDTO> update(@PathVariable Long id, @RequestBody CreateHostDTO host) {
        return hostService.update(id, host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Избриши домаќин", description = "Го брише записот за домаќин со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешно избришан домаќин"),
            @ApiResponse(responseCode = "404", description = "Домаќинот не е пронајден")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Get hosts by country", description = "Retrieves a list of hosts by country.")
    @GetMapping("/list-by-country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пронајдени домаќини за дадената држава"),
            @ApiResponse(responseCode = "404", description = "Домаќините за дадената држава не се пронајдени")
    })
    public ResponseEntity<List<DisplayHostDTO>> findHostsByCountry(@RequestParam Long countryId) {
        List<DisplayHostDTO> hosts = hostService.findByCountryId(countryId);
        return hosts.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(hosts);
    }

}
