package moviestreamingservice.domain.city;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.cinema.CinemaMapper;
import moviestreamingservice.domain.cinema.CinemaRepository;
import moviestreamingservice.domain.cinema.dto.CinemaResponse;
import moviestreamingservice.domain.city.dto.CityResponse;
import moviestreamingservice.domain.city.dto.CityRequest;
import moviestreamingservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CinemaRepository cinemaRepository;
    private final CityMapper cityMapper;
    public List<CityResponse> getAllCities() {
        return cityRepository.findAll().stream()
                .map(cityMapper::cityToCityResponse)
                .toList();
    }
    public CityResponse getCityById(Long id){
        City city = cityRepository.findById(id).orElseThrow(()->new NotFoundException("City not found"));
        return cityMapper.cityToCityResponse(city);
    }
    public List<CinemaResponse> getCinemasByCity(Long cityId){
        if(!cityRepository.existsById(cityId)){
            throw new NotFoundException("City not found");
        }
        return cinemaRepository.findByCityId(cityId).stream().map(CinemaMapper::toResponse).toList();
    }
    public CityResponse createCity(CityRequest request){
        City city = new City();
        city.setName(request.name());
        City savedCity = cityRepository.save(city);
        return cityMapper.cityToCityResponse(savedCity);

    }
    public CityResponse updateCity(Long id, CityRequest request) {
        City city = cityRepository.findById(id).orElseThrow(()->new NotFoundException("City not found"));
        city.setName(request.name());
        City updated = cityRepository.save(city);
        return cityMapper.cityToCityResponse(updated);

    }
    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new NotFoundException("City not found");
        }
        cityRepository.deleteById(id);
    }
}
