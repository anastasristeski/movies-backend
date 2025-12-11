package moviestreamingservice.domain.movie.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.dto.MovieDetailsResponse;
import moviestreamingservice.domain.movie.service.MovieService;
import moviestreamingservice.domain.movie.dto.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class PublicMovieController {
    private final MovieService movieService;
    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }
    @GetMapping
    ResponseEntity<Page<MovieResponse>> getPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size
    ) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(movieService.getAll(pageable));
    }
    @GetMapping("/{tmdbId}")
    public ResponseEntity<MovieDetailsResponse> getMovie(@PathVariable @Positive Long tmdbId){
        return ResponseEntity.ok(movieService.getDetailsById(tmdbId));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<MovieResponse>> searchMovies(@RequestParam String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size){
        return ResponseEntity.ok(movieService.searchMovies(query,page,size));
    }
}