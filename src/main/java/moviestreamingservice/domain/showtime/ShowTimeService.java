package moviestreamingservice.domain.showtime;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.hall.Hall;
import moviestreamingservice.domain.hall.HallRepository;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.MovieRepository;
import moviestreamingservice.domain.reservation.ReservationRepository;
import moviestreamingservice.domain.showtime.dto.ShowTimeRequest;
import moviestreamingservice.domain.showtime.dto.ShowTimeResponse;
import moviestreamingservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeService {
    private final ShowtimeRepository showtimeRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;

    public List<ShowTimeResponse> getShowTimesByHall(Long hallId) {
        if(!hallRepository.existsById(hallId)){
            throw new NotFoundException("Hall with id " + hallId + " not found");
        }
        return showtimeRepository.findByHallId(hallId).stream().map(st-> {
            int reservedSeats = reservationRepository.countReservationsForShowtime(st.getId());
            return ShowTimeMapper.toResponse(st, reservedSeats);
        }).toList();
    }
    public ShowTimeResponse createShowTime(Long hallId, Long movieId, ShowTimeRequest showTimeRequest) {
        Hall hall = hallRepository.findById(hallId).orElseThrow(()-> new NotFoundException("Hall not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new NotFoundException("Movie not found"));
        ShowTime showTime = new ShowTime();

        showTime.setHall(hall);
        showTime.setMovie(movie);
        showTime.setPricePerSeat(showTimeRequest.pricePerSeat());
        showTime.setStartTime(showTimeRequest.startTime());

        return ShowTimeMapper.toResponse(showtimeRepository.save(showTime), reservationRepository.countReservationsForShowtime(showTime.getId()));
    }
    public List<ShowTimeResponse> getShowTimesByCinemaAndDate(Long cinemaId, LocalDate date) {
        return showtimeRepository.findByCinemaAndDate(cinemaId, date)
                .stream()
                .map(st-> {
                    int reservedSeats = reservationRepository.countReservationsForShowtime(st.getId());
                    return ShowTimeMapper.toResponse(st, reservedSeats);
                }).toList();
    }
    public List<ShowTimeResponse> getShowTimesByCinema(Long cinemaId) {
        return showtimeRepository.findByHall_Cinema_Id(cinemaId).stream()
                .map(st-> {
                    int reservedSeats = reservationRepository.countReservationsForShowtime(st.getId());
                    return ShowTimeMapper.toResponse(st, reservedSeats);
                })
                .toList();
    }
    public List<ShowTimeResponse> getShowTimesByMovie(Long movieId) {
        return showtimeRepository.findByMovie_TmdbId(movieId).stream()
                .map(st-> {
                    int reservedSeats = reservationRepository.countReservationsForShowtime(st.getId());
                    return ShowTimeMapper.toResponse(st, reservedSeats);
                })
                .toList();
    }
    public List<ShowTimeResponse> getShowTimesByCinemaAndMovie(Long cinemaId, Long movieId) {
        return showtimeRepository.findByHall_Cinema_IdAndMovie_TmdbId(cinemaId, movieId).stream()
                .map(st-> {
                    int reservedSeats = reservationRepository.countReservationsForShowtime(st.getId());
                    return ShowTimeMapper.toResponse(st, reservedSeats);
                })
                .toList();
    }
    public ShowTimeResponse getShowTime(Long id) {
        ShowTime showTime = showtimeRepository.findById(id).orElseThrow(()->new NotFoundException("ShowTime not found"));
        return ShowTimeMapper.toResponse(showTime, reservationRepository.countReservationsForShowtime(showTime.getId()));
    }
    public void deleteShowTime(Long id) {
        if(!showtimeRepository.existsById(id)){
            throw new NotFoundException("ShowTime not found");
        }
        showtimeRepository.deleteById(id);
    }

}
