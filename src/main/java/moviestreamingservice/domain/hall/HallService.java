package moviestreamingservice.domain.hall;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.Cinema;
import moviestreamingservice.domain.cinema.CinemaRepository;
import moviestreamingservice.domain.hall.dto.HallRequest;
import moviestreamingservice.domain.hall.dto.HallResponse;
import moviestreamingservice.exception.NotFoundException;
import moviestreamingservice.utilities.SeatUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;
    public List<HallResponse> getHallsByCinema(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new NotFoundException("Cinema not found"));
        return cinema.getHalls().stream()
                .map(HallMapper::mapHallToResponse).toList();
    }
    public HallResponse getHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(()->new NotFoundException("Hall not found"));
        return HallMapper.mapHallToResponse(hall);
    }
    public HallResponse createHall(Long cinemaId, HallRequest hallRequest) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(()->new NotFoundException("Cinema not found"));
        Hall hall = new Hall();

        hall.setHallNumber(hallRequest.hallNumber());
        hall.setTotalSeats(hallRequest.totalSeats());
        hall.setCinema(cinema);
        hall.setSeats(SeatUtil.generateSeats(hallRequest.totalSeats()));

        Hall saved = hallRepository.save(hall);
        return HallMapper.mapHallToResponse(saved);
    }
    public HallResponse updateHall(Long id, HallRequest hallRequest) {
        Hall hall = hallRepository.findById(id).orElseThrow(()-> new NotFoundException("Hall not found"));
        hall.setHallNumber(hallRequest.hallNumber());
        hall.setTotalSeats(hallRequest.totalSeats());
        hall.setSeats(SeatUtil.generateSeats(hallRequest.totalSeats()));
        return  HallMapper.mapHallToResponse(hallRepository.save(hall));
    }
    public void deleteHall(Long id) {
        if(!hallRepository.existsById(id)){
            throw new NotFoundException("Hall not found");
        }
        hallRepository.deleteById(id);
    }
}
