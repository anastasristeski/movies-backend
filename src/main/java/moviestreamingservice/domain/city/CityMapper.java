package moviestreamingservice.domain.city;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.CinemaRepository;
import moviestreamingservice.domain.city.dto.CityResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityMapper {
    private  final CinemaRepository cinemaRepository;
    public  CityResponse cityToCityResponse(City city) {
        long cinemaCount = cinemaRepository.countByCityId(city.getId());
        return new CityResponse(city.getId(), city.getName(), (int) cinemaCount);
    }
}
