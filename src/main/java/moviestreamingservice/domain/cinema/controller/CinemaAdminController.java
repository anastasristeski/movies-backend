package moviestreamingservice.domain.cinema.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.Cinema;
import moviestreamingservice.domain.cinema.CinemaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/cinema")
@RequiredArgsConstructor
public class CinemaAdminController {
    private final CinemaService cinemaService;
    @PostMapping("/city/{cityId}")
    public Cinema createCinema(@PathVariable Long cityId, @RequestBody Cinema cinema) {
        return cinemaService.createCinema(cityId, cinema);
    }
    @PutMapping("/{id}")
    public Cinema updateCinema(@PathVariable Long id, @RequestBody Cinema updated) {
        return cinemaService.updateCinema(id, updated);
    }
    @DeleteMapping("/{id}")
    public void deleteCinema(@PathVariable Long id) {
        cinemaService.deleteCinema(id);
    }
}
