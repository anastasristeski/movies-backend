package moviestreamingservice.domain.showtime.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.showtime.ShowTime;
import moviestreamingservice.domain.showtime.ShowTimeService;
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
    @GetMapping("/cinema/{cinemaId}")
    public List<ShowTime> getShowtimesByCinema(@PathVariable Long cinemaId) {
        return showTimeService.getShowTimesByCinema(cinemaId);
    }
    @GetMapping("/movie/{movieId}")
    public List<ShowTime> getShowtimesByMovie(@PathVariable Long movieId) {
        return showTimeService.getShowTimesByMovie(movieId);
    }
    @GetMapping("/cinema/{cinemaId}/movie/{movieId}")
    public List<ShowTime> getShowtimesForCinemaAndMovie(@PathVariable Long cinemaId, @PathVariable Long movieId) {
        return showTimeService.getShowTimesByCinemaAndMovie(cinemaId,movieId);
    }

}
