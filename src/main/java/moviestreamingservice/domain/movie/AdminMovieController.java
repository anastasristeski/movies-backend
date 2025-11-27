package moviestreamingservice.domain.movie;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.showtime.ShowTime;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {
    private final MovieService movieService;
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }
    @PutMapping("/{tmdbId}")
    public Movie updateMovie(@PathVariable Long tmdbId, @RequestBody Movie updated) {
        return movieService.updateMovie(tmdbId, updated);
    }
    @DeleteMapping("/{tmdbId}")
    public void deleteMovie(@PathVariable Long tmdbId){
        movieService.deleteMovie(tmdbId);
    }

}
