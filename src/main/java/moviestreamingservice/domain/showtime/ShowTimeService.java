package moviestreamingservice.domain.showtime;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.hall.Hall;
import moviestreamingservice.domain.hall.HallRepository;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.MovieRepository;
import moviestreamingservice.domain.showtime.dto.ShowTimeRequest;
import moviestreamingservice.domain.showtime.dto.ShowTimeResponse;
import moviestreamingservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeService {
    private final ShowtimeRepository showtimeRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;

    public List<ShowTimeResponse> getShowTimesByHall(Long hallId) {
        Hall hall = hallRepository.findById(hallId).orElseThrow(()->new NotFoundException("Hall not found"));
        return hall.getShowTimes().stream()
                .map(ShowTimeMapper::toResponse)
                .toList();
    }
    public ShowTimeResponse createShowTime(Long hallId, Long movieId, ShowTimeRequest showTimeRequest) {
        Hall hall = hallRepository.findById(hallId).orElseThrow(()-> new NotFoundException("Hall not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new NotFoundException("Movie not found"));
        ShowTime showTime = new ShowTime();

        showTime.setHall(hall);
        showTime.setMovie(movie);
        showTime.setPricePerSeat(showTimeRequest.pricePerSeat());
        showTime.setStartTime(showTimeRequest.startTime());

        return ShowTimeMapper.toResponse(showtimeRepository.save(showTime));
    }
    public List<ShowTimeResponse> getShowTimesByCinema(Long cinemaId) {
        return showtimeRepository.findByHall_Cinema_Id(cinemaId).stream()
                .map(ShowTimeMapper::toResponse)
                .toList();
    }
    public List<ShowTimeResponse> getShowTimesByMovie(Long movieId) {
        return showtimeRepository.findByMovie_TmdbId(movieId).stream()
                .map(ShowTimeMapper::toResponse)
                .toList();
    }
    public List<ShowTimeResponse> getShowTimesByCinemaAndMovie(Long cinemaId, Long movieId) {
        return showtimeRepository.findByHall_Cinema_IdAndMovie_TmdbId(cinemaId, movieId).stream()
                .map(ShowTimeMapper::toResponse)
                .toList();
    }
    public ShowTimeResponse getShowTime(Long id) {
        ShowTime showTime = showtimeRepository.findById(id).orElseThrow(()->new NotFoundException("ShowTime not found"));
        return ShowTimeMapper.toResponse(showTime);
    }
    public void deleteShowTime(Long id) {
        if(!showtimeRepository.existsById(id)){
            throw new NotFoundException("ShowTime not found");
        }
        showtimeRepository.deleteById(id);
    }

}
