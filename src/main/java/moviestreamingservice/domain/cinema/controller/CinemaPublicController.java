package moviestreamingservice.domain.cinema.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.Cinema;
import moviestreamingservice.domain.cinema.CinemaService;
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
    @GetMapping("/city/{id}")
    public List<Cinema> getCinemasByCity(@PathVariable Long id) {
        return cinemaService.getCinemaByCity(id);
    }
    @GetMapping("/{id}")
    public Cinema getCinema(@PathVariable Long id) {
        return cinemaService.getCinemaById(id);

    }
}
