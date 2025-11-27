package moviestreamingservice.domain.hall;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.Cinema;
import moviestreamingservice.domain.cinema.CinemaRepository;
import moviestreamingservice.utilities.SeatUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;
    public List<Hall> getHallsByCinema(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("Cinema not found"));
        return cinema.getHalls();
    }
    public Hall getHall(Long id) {
        return hallRepository.findById(id).orElseThrow(()->new RuntimeException("Hall not found"));
    }
    public Hall createHall(Long cinemaId, Hall hall) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(()->new RuntimeException("Cinema not found"));
        hall.setCinema(cinema);
        hall.setSeats(SeatUtil.generateSeats(hall.getTotalSeats()));
        return hallRepository.save(hall);
    }
    public Hall updateHall(Long id, Hall updated) {
        Hall hall = getHall(id);
        hall.setHallNumber(updated.getHallNumber());
        hall.setTotalSeats(updated.getTotalSeats());
        return hallRepository.save(hall);
    }
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}
