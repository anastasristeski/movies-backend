package moviestreamingservice.domain.hall.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.hall.Hall;
import moviestreamingservice.domain.hall.HallService;
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
    public Hall getHall(@PathVariable Long id) {
        return hallService.getHall(id);
    }
    @GetMapping("/cinema/{cinemaId}")
    public List<Hall> getHallsForCinema(@PathVariable Long cinemaId) {
        return hallService.getHallsByCinema(cinemaId);
    }

}
