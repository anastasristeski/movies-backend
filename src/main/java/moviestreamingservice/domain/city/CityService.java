package moviestreamingservice.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    public City getCityById(Long id){
        return cityRepository.findById(id).orElseThrow(()->new RuntimeException("City not found"));
    }
    public City createCity(City city){
        return cityRepository.save(city);
    }
    public City updateCity(Long id, City updated) {
        City city = getCityById(id);
        city.setName(updated.getName());
        return cityRepository.save(city);
    }
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
