package moviestreamingservice.domain.showtime.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.showtime.ShowTimeService;
import moviestreamingservice.domain.showtime.dto.ShowTimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/showtime")
@RequiredArgsConstructor
public class ShowTimePublicController {
    private final ShowTimeService showTimeService;
    @GetMapping("/{id}")
    public ResponseEntity<ShowTimeResponse> getShowTime(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(showTimeService.getShowTime(id));
    }
    @GetMapping("/cinema/{cinemaId}")
    public ResponseEntity<List<ShowTimeResponse>> getShowTimesByCinemaAndDate(@PathVariable @Positive Long cinemaId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(showTimeService.getShowTimesByCinemaAndDate(cinemaId,date));
    }
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowTimeResponse>> getShowTimesByMovie(@PathVariable @Positive Long movieId) {
        return ResponseEntity.ok(showTimeService.getShowTimesByMovie(movieId));
    }
    @GetMapping("/cinema/{cinemaId}/movie/{movieId}")
    public ResponseEntity<List<ShowTimeResponse>> getShowTimesForCinemaAndMovie(
            @PathVariable @Positive Long cinemaId,
            @Positive @PathVariable Long movieId) {
        return ResponseEntity.ok(showTimeService.getShowTimesByCinemaAndMovie(cinemaId,movieId));
    }
}
