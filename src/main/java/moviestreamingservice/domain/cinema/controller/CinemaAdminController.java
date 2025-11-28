package moviestreamingservice.domain.cinema.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.CinemaService;
import moviestreamingservice.domain.cinema.dto.CinemaRequest;
import moviestreamingservice.domain.cinema.dto.CinemaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/cinema")
@RequiredArgsConstructor
public class CinemaAdminController {
    private final CinemaService cinemaService;
    @PostMapping("/city/{cityId}")
    public ResponseEntity<CinemaResponse> createCinema(
            @PathVariable @Positive Long cityId,
            @Valid @RequestBody CinemaRequest cinemaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cinemaService.createCinema(cityId, cinemaRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CinemaResponse> updateCinema(
            @PathVariable Long id,
            @RequestBody CinemaRequest updatedRequest) {
        return ResponseEntity.ok(cinemaService.updateCinema(id,updatedRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable @Positive Long id) {
        cinemaService.deleteCinema(id);
        return ResponseEntity.noContent().build();
    }
}
