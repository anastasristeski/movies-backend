package moviestreamingservice.domain.cinema;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.city.City;
import moviestreamingservice.domain.city.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;
    private final CityRepository cityRepository;
    public List<Cinema> getCinemaByCity(Long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
        return city.getCinemas();
    }
    public Cinema getCinemaById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId).orElseThrow(()-> new RuntimeException("City not found"));
    }
    public Cinema createCinema(Long cityId, Cinema cinema) {
        City city = cityRepository.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
        cinema.setCity(city);
        return cinemaRepository.save(cinema);
    }
    public void deleteCinema(Long cinemaId) {
        cinemaRepository.deleteById(cinemaId);
    }
    public Cinema updateCinema(Long id, Cinema updated) {
        Cinema cinema = getCinemaById(id);
        cinema.setName(updated.getName());
        cinema.setCity(updated.getCity());
        cinema.setHalls(updated.getHalls());
        return cinemaRepository.save(cinema);
    }
}
