package moviestreamingservice.domain.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class PublicMovieController {
    private final MovieService movieService;
//   TODO PAGEABLE WITH SERVICE
//    @GetMapping
//    public List<Movie> getAllMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size){
//        Pageable pageable = PageRequest.of(page, size);
//        return movieRepository.findAll(pageable).getContent();
//    }
    public Movie getMovie(@PathVariable Long tmdbId){
        return movieService.getById(tmdbId);
    }
}