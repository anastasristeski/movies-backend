package moviestreamingservice.domain.showtime.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.showtime.ShowTimeService;
import moviestreamingservice.domain.showtime.dto.ShowTimeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<ShowTimeResponse>> getShowTimesByCinema(@PathVariable @Positive Long cinemaId) {
        return ResponseEntity.ok(showTimeService.getShowTimesByCinema(cinemaId));
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
