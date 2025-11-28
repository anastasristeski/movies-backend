package moviestreamingservice.domain.city;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.city.dto.CityResponse;
import moviestreamingservice.domain.city.dto.CityRequest;
import moviestreamingservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    public List<CityResponse> getAllCities() {
        return cityRepository.findAll().stream()
                .map(city -> new CityResponse(city.getId(), city.getName()))
                .toList();
    }
    public CityResponse getCityById(Long id){
        City city = cityRepository.findById(id).orElseThrow(()->new NotFoundException("City not found"));
        return new CityResponse(city.getId(), city.getName());
    }
    public CityResponse createCity(CityRequest request){
        City city = new City();
        city.setName(request.name());
        City savedCity = cityRepository.save(city);
        return new CityResponse(savedCity.getId(), savedCity.getName());
    }
    public CityResponse updateCity(Long id, CityRequest request) {
        City city = cityRepository.findById(id).orElseThrow(()->new NotFoundException("City not found"));
        city.setName(request.name());
        City updated = cityRepository.save(city);
        return new CityResponse(updated.getId(), updated.getName());
    }
    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new NotFoundException("City not found");
        }
        cityRepository.deleteById(id);
    }
}
