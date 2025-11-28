package moviestreamingservice.domain.showtime.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.showtime.ShowTimeService;
import moviestreamingservice.domain.showtime.dto.ShowTimeRequest;
import moviestreamingservice.domain.showtime.dto.ShowTimeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/showtime")
@RequiredArgsConstructor
public class ShowTimeAdminController {
    private final ShowTimeService showTimeService;
    @PostMapping("/hall/{hallId}/movie/{movieId}")
    public ResponseEntity<ShowTimeResponse> createShowTime(
            @PathVariable @Positive Long hallId,
            @PathVariable @Positive Long movieId,
            @Valid @RequestBody ShowTimeRequest showTimeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(showTimeService.createShowTime(hallId,movieId,showTimeRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowTime(@PathVariable @Positive Long id) {
        showTimeService.deleteShowTime(id);
        return ResponseEntity.noContent().build();
    }
}
