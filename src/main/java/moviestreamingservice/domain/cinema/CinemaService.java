package moviestreamingservice.domain.cinema;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.dto.CinemaRequest;
import moviestreamingservice.domain.cinema.dto.CinemaResponse;
import moviestreamingservice.domain.city.City;
import moviestreamingservice.domain.city.CityRepository;
import moviestreamingservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    private final CityRepository cityRepository;
    public List<CinemaResponse> getAllCinemas() {
        return cinemaRepository.findAll().stream()
                .map(cinema -> new CinemaResponse(
                        cinema.getId(),
                        cinema.getName(),
                        cinema.getCity().getId())
                )
                .toList();
    }
    public List<CinemaResponse> getCinemaByCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(()->new NotFoundException("City not found"));
        return city.getCinemas().stream()
                .map(cinema-> new CinemaResponse(
                        cinema.getId(),
                        cinema.getName(),
                        cityId
                ))
                .toList();
    }
    public CinemaResponse getCinemaById(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(()-> new NotFoundException("City not found"));
        return new CinemaResponse(
                cinema.getId(),
                cinema.getName(),
                cinema.getCity().getId()
        );
    }
    public CinemaResponse createCinema(Long cityId, CinemaRequest cinemaRequest) {
        City city = cityRepository.findById(cityId).orElseThrow(()->new NotFoundException("City not found"));
        Cinema cinema = new Cinema();
        cinema.setName(cinemaRequest.name());
        cinema.setCity(city);
        Cinema savedCinema = cinemaRepository.save(cinema);
        return new CinemaResponse(
                savedCinema.getId(),
                savedCinema.getName(),
                cityId
        );
    }

    public CinemaResponse updateCinema(Long id, CinemaRequest cinemaRequest) {
       Cinema cinema = cinemaRepository.findById(id).orElseThrow(()->new NotFoundException("Cinema not found"));
       cinema.setName(cinemaRequest.name());
       Cinema updatedCinema = cinemaRepository.save(cinema);
       return new CinemaResponse(
               updatedCinema.getId(),
               updatedCinema.getName(),
               updatedCinema.getCity().getId()
       );
    }
    public void deleteCinema(Long cinemaId) {
        if(!cinemaRepository.existsById(cinemaId)){
            throw new NotFoundException("Cinema not found");
        }
        cinemaRepository.deleteById(cinemaId);
    }
}
