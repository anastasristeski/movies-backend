package moviestreamingservice.domain.hall.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.hall.HallService;
import moviestreamingservice.domain.hall.dto.HallRequest;
import moviestreamingservice.domain.hall.dto.HallResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/hall")
@RequiredArgsConstructor
public class HallAdminController {
    private final HallService hallService;
    @PostMapping("/cinema/{cinemaId}")
    public ResponseEntity<HallResponse> createHall(
            @PathVariable @Positive Long cinemaId,
            @Valid @RequestBody HallRequest hallRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hallService.createHall(cinemaId, hallRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<HallResponse> updateHall(
            @PathVariable @Positive Long id,
            @Valid @RequestBody HallRequest hallRequest) {
        return ResponseEntity.ok(hallService.updateHall(id, hallRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable @Positive Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}
