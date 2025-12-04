package moviestreamingservice.domain.movie.service;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.MovieMapper;
import moviestreamingservice.domain.movie.MovieRepository;
import moviestreamingservice.domain.movie.dto.MovieDetailsResponse;
import moviestreamingservice.domain.movie.dto.MovieRequest;
import moviestreamingservice.domain.movie.dto.MovieResponse;
import moviestreamingservice.exception.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieTrailerService movieTrailerService;
    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movie = MovieMapper.toEntity(movieRequest);
        return MovieMapper.toResponse(movieRepository.save(movie));
    }
    public MovieResponse updateMovie(Long id, MovieRequest updatedReq){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found"));

        movie.setTitle(updatedReq.title());
        movie.setOverview(updatedReq.overview());
        movie.setPosterUrl(updatedReq.posterUrl());
        movie.setBackdropUrl(updatedReq.backdropUrl());
        movie.setVoteAverage(updatedReq.voteAverage());
        movie.setVoteCount(updatedReq.voteCount());
        movie.setPopularity(updatedReq.popularity());
        movie.setReleaseDate(updatedReq.releaseDate());
        movie.setGenres(updatedReq.genres());

        Movie saved = movieRepository.save(movie);

        return MovieMapper.toResponse(saved);
    }
    public void deleteMovie(Long id) {
        if(!movieRepository.existsById(id)) {
            throw new NotFoundException("Movie not found");
        }
        movieRepository.deleteById(id);
    }
    public List<MovieResponse> getAll() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toResponse)
                .toList();
    }
    public MovieResponse getById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Movie not found"));
        return MovieMapper.toResponse(movie);
    }
    public MovieDetailsResponse getDetailsById(Long id)  {
        MovieResponse movieResponse = getById(id);
        String trailerKey = movieTrailerService.getTrailerKey(id);
        return new MovieDetailsResponse(movieResponse, trailerKey);
    }
    public List<MovieResponse> getAll(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .stream()
                .map(MovieMapper::toResponse)
                .toList();
    }
}
