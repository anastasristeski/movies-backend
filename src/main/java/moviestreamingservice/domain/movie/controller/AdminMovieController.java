package moviestreamingservice.domain.movie.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.movie.MovieService;
import moviestreamingservice.domain.movie.dto.MovieRequest;
import moviestreamingservice.domain.movie.dto.MovieResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {
    private final MovieService movieService;
    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest movieRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movieService.createMovie(movieRequest));
    }
    @PutMapping("/{tmdbId}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable @Positive Long tmdbId, @Valid @RequestBody MovieRequest movieRequest) {
        return ResponseEntity.ok(movieService.updateMovie(tmdbId,movieRequest));
    }
    @DeleteMapping("/{tmdbId}")
    public ResponseEntity<Void> delete(@PathVariable Long tmdbId){
        movieService.deleteMovie(tmdbId);
        return ResponseEntity.noContent().build();
    }

}
