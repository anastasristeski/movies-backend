package moviestreamingservice.domain.hall.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.hall.Hall;
import moviestreamingservice.domain.hall.HallService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/hall")
@RequiredArgsConstructor
public class HallAdminController {
    private final HallService hallService;
    @PostMapping("/cinema/{cinemaId}")
    public Hall createHall(@PathVariable Long cinemaId, @RequestBody Hall hall) {
        return hallService.createHall(cinemaId, hall);
    }
    @PutMapping("/{id}")
    public Hall updateHall(@PathVariable Long id, @RequestBody Hall updated) {
        return hallService.updateHall(id,updated);
    }
    @DeleteMapping("/{id}")
    public void deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
    }
}
