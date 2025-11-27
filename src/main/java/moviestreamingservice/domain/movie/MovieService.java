package moviestreamingservice.domain.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }
    public Movie updateMovie(Long id, Movie updated){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Movie not found"));
        movie.setTitle(updated.getTitle());
        movie.setOverview(updated.getOverview());
        movie.setPosterUrl(updated.getPosterUrl());
        movie.setGenres(updated.getGenres());
        return movieRepository.save(movie);
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }
    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Movie not found"));
    }
}
