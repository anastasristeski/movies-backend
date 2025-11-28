package moviestreamingservice.domain.hall.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.hall.HallService;
import moviestreamingservice.domain.hall.dto.HallResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
public class HallPublicController {
    private final HallService hallService;
    @GetMapping("/{id}")
    public ResponseEntity<HallResponse> getHall(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(hallService.getHall(id));
    }
    @GetMapping("/cinema/{cinemaId}")
    public ResponseEntity<List<HallResponse>> getHallsForCinema(@PathVariable @Positive Long cinemaId) {
        return ResponseEntity.ok(hallService.getHallsByCinema(cinemaId));
    }

}
