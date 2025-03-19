package mk.ukim.finki.airbnblab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.airbnblab.model.Host;
import mk.ukim.finki.airbnblab.model.dto.HostDto;
import mk.ukim.finki.airbnblab.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host", description = "API за управување со домаќини")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "Превземи сите домаќини", description = "Ги враќа сите домаќини во системот")
    public ResponseEntity<List<Host>> findAll() {
        List<Host> hosts = hostService.findAll();
        return ResponseEntity.ok(hosts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Превземи домаќин по ID", description = "Го враќа записот за домаќин со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пронајден домаќин"),
            @ApiResponse(responseCode = "404", description = "Домаќинот не е пронајден")
    })
    public ResponseEntity<Host> findById(@PathVariable Long id) {
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
    public ResponseEntity<Host> save(@RequestBody HostDto host) {
        Optional<Host> savedHost = hostService.save(host);
        return savedHost.map(h -> ResponseEntity.status(201).body(h))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Измени постоечки домаќин", description = "Ажурира запис за домаќин со дадено ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно ажуриран домаќин"),
            @ApiResponse(responseCode = "400", description = "Грешка при ажурирање"),
            @ApiResponse(responseCode = "404", description = "Домаќинот не е пронајден")
    })
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostDto host) {
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
}
