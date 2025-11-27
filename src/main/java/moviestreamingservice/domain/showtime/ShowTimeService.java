package moviestreamingservice.domain.showtime;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.hall.Hall;
import moviestreamingservice.domain.hall.HallRepository;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeService {
    private final ShowtimeRepository showtimeRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;

    public List<ShowTime> getShowTimesByHall(Long hallId) {
        Hall hall = hallRepository.findById(hallId).orElseThrow(()->new RuntimeException("Hall not found"));
        return hall.getShowTimes();
    }
    public ShowTime createShowTime(Long hallId, Long movieId, ShowTime showTime) {
        Hall hall = hallRepository.findById(hallId).orElseThrow(()-> new RuntimeException("Hall not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new RuntimeException("Movie not found"));
        showTime.setHall(hall);
        showTime.setMovie(movie);
        return showtimeRepository.save(showTime);
    }
    public List<ShowTime> getShowTimesByCinema(Long cinemaId) {
        return showtimeRepository.findByHall_Cinema_Id(cinemaId);
    }
    public List<ShowTime> getShowTimesByMovie(Long movieId) {
        return showtimeRepository.findByMovie_TmdbId(movieId);
    }
    public List<ShowTime> getShowTimesByCinemaAndMovie(Long cinemaId, Long movieId) {
        return showtimeRepository.findByHall_Cinema_IdAndMovie_TmdbId(cinemaId, movieId);
    }
    public ShowTime getShowTime(Long id) {
        return showtimeRepository.findById(id).orElseThrow(()->new RuntimeException("Showtime not found"));
    }
    public void deleteShowTime(Long id) {
        showtimeRepository.deleteById(id);
    }

}
