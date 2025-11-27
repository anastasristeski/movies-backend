package moviestreamingservice.domain.showtime.controller;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.showtime.ShowTime;
import moviestreamingservice.domain.showtime.ShowTimeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/showtime")
@RequiredArgsConstructor
public class ShowTimeAdminController {
    private final ShowTimeService showTimeService;
    @PostMapping("/hall/{hallId}/movie/{movieId}")
    public ShowTime createShowTime(@PathVariable Long hallId, @PathVariable Long movieId, @RequestBody ShowTime showTime) {
        return showTimeService.createShowTime(hallId, movieId, showTime);
    }
    @DeleteMapping("/{id}")
    public void deleteShowTime(@PathVariable Long id) {
        showTimeService.deleteShowTime(id);
    }
}
