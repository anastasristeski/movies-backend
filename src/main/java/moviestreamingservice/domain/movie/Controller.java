package moviestreamingservice.domain.movie;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private final MovieRepository movieRepository;
    public Controller(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/api/v1/movies")
    public List<Movie> getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size){
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable).getContent();
    }
}