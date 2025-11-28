package moviestreamingservice.domain.cinema.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.CinemaService;
import moviestreamingservice.domain.cinema.dto.CinemaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
@RequiredArgsConstructor
public class CinemaPublicController {
    private final CinemaService cinemaService;
    @GetMapping
    public ResponseEntity<List<CinemaResponse>> getAllCinemas() {
       return ResponseEntity.ok(cinemaService.getAllCinemas());
    }
    @GetMapping("/city/{id}")
    public ResponseEntity<List<CinemaResponse>> getCinemasByCity(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(cinemaService.getCinemaByCity(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CinemaResponse> getCinema(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(cinemaService.getCinemaById(id));

    }
}
